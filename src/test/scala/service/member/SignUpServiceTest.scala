package service.member

import datasource._Database
import datasource.account.AccountRepository
import datasource.member.MemberRepository
import domain.account.Password
import domain.member.{Course, Id}
import org.scalatest.{BeforeAndAfter, FunSuite}

class SignUpServiceTest extends FunSuite with BeforeAndAfter {

  before {
    _Database.initialize()
  }

  // OK

  test("basic sign up") {
    // no member 1
    assert(!AccountRepository.isExists(Id("1"), Password("ps_1")))

    // apply
    SignUpService.apply(SignUpRequest.basic)

    // my name and basic and member 1
    assert(MemberRepository.findOneBy(Id("1")).get.name == SignUpRequest.basic.name)
    assert(MemberRepository.findOneBy(Id("1")).get.course == Course.basic)
    assert(AccountRepository.isExists(Id("1"), Password("ps_1")))
  }

  test("niconico sign up") {
    // no member 1
    assert(!AccountRepository.isExists(Id("1"), Password("ps_1")))

    // apply
    SignUpService.apply(SignUpRequest.niconico)

    // my name and niconico and member 1
    assert(MemberRepository.findOneBy(Id("1")).get.name == SignUpRequest.niconico.name)
    assert(MemberRepository.findOneBy(Id("1")).get.course == Course.niconico)
    assert(AccountRepository.isExists(Id("1"), Password("ps_1")))
  }

  // NG

  test("signed up name") {
    // sign up
    SignUpService.apply(SignUpRequest.basic)

    val thrown = intercept[AssertionError] {
      // sign up twice
      SignUpService.apply(SignUpRequest.basic)
    }

    assert(thrown.getMessage.endsWith("already signed up"))
  }

  test("minor") {
    val thrown = intercept[AssertionError] {
      // under 20 years old request
      SignUpService.apply(SignUpRequest.invalidBirthDate)
    }

    assert(thrown.getMessage.endsWith("minor is not applicable"))
  }

  test("invalid credit card") {
    val thrown = intercept[AssertionError] {
      // invalid credit card request
      SignUpService.apply(SignUpRequest.invalidCreditCard)
    }

    assert(thrown.getMessage.endsWith("invalid credit card"))
  }
}
