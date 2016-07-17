package service.niconico

import datasource.account.AccountRepository
import datasource.member.MemberRepository
import domain.account.Password
import domain.member.Id

object AuthorizationService {
  def assertAccount(id: Id, password: Password): Unit = {
    assert(AccountRepository.isExists(id, password), "no such member")

    val member = MemberRepository.findOneBy(id, MemberRepository.noSuchMember)

    assert(member.course.isNiconico, "not niconico course")
    assert(!member.state.isResignExecuted, "no such member")
  }
}
