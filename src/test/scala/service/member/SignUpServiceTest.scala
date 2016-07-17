package service.member

import datasource._Database
import datasource.account.AccountRepository
import datasource.member.MemberRepository
import domain.member.{Password, Id}
import org.scalatest.{BeforeAndAfter, FunSuite}

class SignUpServiceTest extends FunSuite with BeforeAndAfter {

  before {
    _Database.initialize()
  }

  // OK

  test("sign up") {
    assert(!AccountRepository.isExists(Id("1"), Password("ps_1")))

    SignUpService.apply(SignUpRequest.valid)

    assert(MemberRepository.findOneBy(Id("1")).get.name == SignUpRequest.valid.name)
    assert(AccountRepository.isExists(Id("1"), Password("ps_1")))
  }

  // NG

  test("signed up name") {
    SignUpService.apply(SignUpRequest.valid)

    val thrown = intercept[AssertionError] {
      SignUpService.apply(SignUpRequest.valid)
    }

    assert(thrown.getMessage.endsWith("already signed up"))
  }

  test("minor") {
    val thrown = intercept[AssertionError] {
      SignUpService.apply(SignUpRequest.invalidBirthDate)
    }

    assert(thrown.getMessage.endsWith("minor is not applicable"))
  }

  test("invalid credit card") {
    val thrown = intercept[AssertionError] {
      SignUpService.apply(SignUpRequest.invalidCreditCard)
    }

    assert(thrown.getMessage.endsWith("invalid credit card"))
  }
}
