package service.member

import datasource.account.AccountRepository
import datasource.member.MemberRepository
import domain.account.Password
import domain.member.{Id, Member}

object ResignService {
  def refer(id: Id, password: Password): Member = {
    assertResignable(id, password)

    MemberRepository.findOneBy(id, id => new RuntimeException("no such member: %s".format(id)))
  }

  def apply(id: Id, password: Password): Unit = {
    assertResignable(id, password)

    val member = MemberRepository.findOneBy(id, id => new RuntimeException("no such member: %s".format(id)))

    MemberRepository.save(member.resignApplication())
  }

  private def assertResignable(id: Id, password: Password): Unit = {
    assert(AccountRepository.isExists(id, password), "invalid id or password")
    assert(MemberRepository.findOneBy(id).exists(_.state.resign.isEmpty), "invalid state for resign")
  }

  def executeAll(): Unit = {
    MemberRepository.resignApplied().foreach(
      it => MemberRepository.save(it.resignExecution())
    )
  }
}
