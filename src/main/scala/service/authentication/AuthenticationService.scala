package service.authentication

import domain.member.{Id, Password}
import util.Dummies

object AuthenticationService {
  def isValid(id: Id, password: Password): Boolean = {
    true // todo impl
  }
}
