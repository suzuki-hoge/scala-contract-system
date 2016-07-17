package service.member

import datasource._Database
import datasource.member.MemberRepository
import domain.member.Id
import org.scalatest.{BeforeAndAfter, FunSuite}

class SignUpServiceTest extends FunSuite with BeforeAndAfter {

  before {
    _Database.initialize()
  }

  test("sign up") {
    SignUpService.apply(SignUpRequest.valid)

    assert(MemberRepository.findOneBy(Id("1")).get.name == SignUpRequest.valid.name)
  }

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
