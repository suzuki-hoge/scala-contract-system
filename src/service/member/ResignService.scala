package service.member

import datasource.{Dummies, MemberRepository, Password}
import domain.member.{Id, Member}
import service.member.authentication.AuthenticationService

object ResignService {
  def refer(id: Id, password: Password): Member = {
    assertResignable(id, password)

    MemberRepository.findOneBy(id) match {
      case Some(x) => x
      case None => throw new RuntimeException("no such member: %s".format(id))
    }
  }

  def apply(id: Id, password: Password): Unit = {
    assertResignable(id, password)

    val member = MemberRepository.findOneBy(id) match {
      case Some(x) => x
      case None => throw new RuntimeException("no such member: %s".format(id))
    }

    MemberRepository.resignApplication(member.resignApplication())
  }

  private def assertResignable(id: Id, password: Password): Unit = {
    assert(AuthenticationService.isValid(id, password), "invalid id or password")
    assert(MemberRepository.findOneBy(id).exists(_.state.isContracted), "invalid state for resign")
  }

  def execute(id: Id): Unit = {
    val member = MemberRepository.findOneBy(id) match {
      case Some(x) => x
      case None => throw new RuntimeException("no such member: %s".format(id))
    }

    MemberRepository.resignExecution(member.resignExecution())
  }
}
