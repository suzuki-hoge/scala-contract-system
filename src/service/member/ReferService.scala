package service.member

import datasource.{Dummies, MemberRepository, Password}
import domain.member.{Id, Member}
import service.member.authentication.AuthenticationService

object ReferService {
  def referForResign(id: Id, password: Password): Member = {
    assertReferable(id, password)

    MemberRepository.findOneBy(id) match {
      case Some(x) => x
      case None => throw new RuntimeException("no such member: %s".format(id))
    }
  }

  private def assertReferable(id: Id, password: Password): Unit = {
    assert(AuthenticationService.isValid(id, password), "invalid id or password")
    assert(MemberRepository.findOneBy(id).exists(_.state.isContracted), "invalid state for resign")
  }
}
