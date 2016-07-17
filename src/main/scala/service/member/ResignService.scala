package service.member

import datasource.member.MemberRepository
import domain.member.{Id, Member, Password}
import service.authentication.AuthenticationService

object ResignService {
  def refer(id: Id, password: Password): Member = {
    assertResignable(id, password)

    MemberRepository.findOneBy(id, id => new RuntimeException("no such member: %s".format(id)))
  }

  def apply(id: Id, password: Password): Unit = {
    assertResignable(id, password)

    val member = MemberRepository.findOneBy(id, id => new RuntimeException("no such member: %s".format(id)))

    MemberRepository.resignApplication(member.resignApplication())
  }

  private def assertResignable(id: Id, password: Password): Unit = {
    assert(AuthenticationService.isValid(id, password), "invalid id or password")
    assert(MemberRepository.findOneBy(id).exists(_.state.isContracted), "invalid state for resign")
  }

  def execute(id: Id): Unit = {
    val member = MemberRepository.findOneBy(id, id => new RuntimeException("no such member: %s".format(id)))

    MemberRepository.resignExecution(member.resignExecution())
  }
}
