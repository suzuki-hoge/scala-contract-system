package service.niconico

import datasource._Database
import datasource.account.AccountRepository
import datasource.member.MemberRepository
import domain.account.Password
import domain.member.Id
import org.scalatest.{BeforeAndAfter, FunSuite}
import service.member.{ResignService, SignUpRequest, SignUpService}

class AuthorizationServiceTest extends FunSuite with BeforeAndAfter {

  before {
    _Database.initialize()
  }

  // OK

  test("ok") {
    SignUpService.apply(SignUpRequest.niconico)

    AuthorizationService.assertAccount(Id("1"), Password("ps_1"))
  }

  // NG

  test("not niconico course") {
    // somebody sign up but not niconico
    SignUpService.apply(SignUpRequest.basic)

    val thrown = intercept[AssertionError] {
      AuthorizationService.assertAccount(Id("1"), Password("ps_1"))
    }

    assert(thrown.getMessage.endsWith("not niconico course"))
  }

  test("no such member") {
    // nobody sign up

    val thrown = intercept[AssertionError] {
      AuthorizationService.assertAccount(Id("1"), Password("ps_1"))
    }

    assert(thrown.getMessage.endsWith("no such member"))
  }

  test("resigned") {
    // somebody sign up
    SignUpService.apply(SignUpRequest.niconico)

    // but resigned
    ResignService.apply(Id("1"), Password("ps_1"))
    ResignService.executeAll()

    val thrown = intercept[AssertionError] {
      AuthorizationService.assertAccount(Id("1"), Password("ps_1"))
    }

    assert(thrown.getMessage.endsWith("no such member"))
  }
}
