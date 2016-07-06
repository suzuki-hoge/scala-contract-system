package service.member.authentication

import datasource.{Dummies, Password}
import domain.member.Id

object AuthenticationService {
  def isValid(id: Id, password: Password): Boolean = {
    (id, password) match {
      case (Dummies.id_signedUp, Dummies.password_valid) => true
      case (Dummies.id_resignApplied, Dummies.password_valid) => true
      case (Dummies.id_resigned, Dummies.password_valid) => true
      case _ => false
    }
  }
}
