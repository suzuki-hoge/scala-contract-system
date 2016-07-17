package service.member

import datasource._Database
import datasource.account.AccountRepository
import datasource.member.MemberRepository
import domain.account.Password
import domain.member.Id
import org.scalatest.{BeforeAndAfter, FunSuite}

class ResignServiceTest extends FunSuite with BeforeAndAfter {

  val id = Id("1")
  val password = Password("ps_1")

  val id2 = Id("2")
  val password2 = Password("ps_2")

  before {
    _Database.initialize()
  }

  // OK

  test("refer") {
    // there is one member
    SignUpService.apply(SignUpRequest.basic)

    // my name
    assert(ResignService.refer(id, password).name == SignUpRequest.basic.name)

    // member is NOT resign applied
    assert(!MemberRepository.findOneBy(id).get.state.isResignApplied)
  }

  test("apply") {
    // there is one member
    SignUpService.apply(SignUpRequest.basic)

    // apply
    ResignService.apply(id, password)

    // member is resign applied
    assert(MemberRepository.findOneBy(id).get.state.isResignApplied)
  }

  test("execute all") {
    // there are two members
    SignUpService.apply(SignUpRequest.basic)
    SignUpService.apply(SignUpRequest.niconico)

    // apply one only
    ResignService.apply(id2, password2)

    // one applied
    assert(MemberRepository.findOneBy(id2).get.state.isResignApplied)

    // execute all for applied member
    ResignService.executeAll()

    // NOT applied
    assert(!MemberRepository.findOneBy(id2).get.state.isResignApplied)
    assert(MemberRepository.findOneBy(id2).get.state.isResignExecuted)

    // but other one is NOT executed
    assert(!MemberRepository.findOneBy(id).get.state.isResignExecuted)
  }

  // NG

  test("authentication failure") {
    // nobody sign up

    val thrown = intercept[AssertionError] {
      ResignService.refer(id, password)
    }

    assert(thrown.getMessage.endsWith("invalid id or password"))
  }

  test("invalid state") {
    // somebody sign up
    SignUpService.apply(SignUpRequest.basic)

    // but resign apply
    ResignService.apply(id, password)

    val thrown = intercept[AssertionError] {
      ResignService.refer(id, password)
    }

    assert(thrown.getMessage.endsWith("invalid state for resign"))
  }

  test("resigned") {
    // somebody sign up
    SignUpService.apply(SignUpRequest.basic)

    // but resigned
    ResignService.apply(Id("1"), Password("ps_1"))
    ResignService.executeAll()

    val thrown = intercept[AssertionError] {
      ResignService.refer(id, password)
    }

    assert(thrown.getMessage.endsWith("no such member"))
  }
}
