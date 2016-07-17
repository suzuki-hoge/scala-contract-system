package service.member

import datasource.account.AccountRepository
import datasource.member.MemberRepository
import domain.account.Password
import domain.member.{Id, Member}

object ResignService {
  def refer(id: Id, password: Password): Member = {
    assertResignable(id, password)

    MemberRepository.findOneBy(id, MemberRepository.noSuchMember)
  }

  def apply(id: Id, password: Password): Unit = {
    assertResignable(id, password)

    val member = MemberRepository.findOneBy(id, MemberRepository.noSuchMember)

    MemberRepository.save(member.resignApplication())
  }

  private def assertResignable(id: Id, password: Password): Unit = {
    assert(AccountRepository.isExists(id, password), "invalid id or password")

    val member = MemberRepository.findOneBy(id, MemberRepository.noSuchMember)

    assert(!member.state.isResignApplied, "invalid state for resign")
    assert(!member.state.isResignExecuted, "no such member")
  }

  def executeAll(): Unit = {
    MemberRepository.resignApplied().foreach(
      it => MemberRepository.save(it.resignExecution())
    )
  }
}
