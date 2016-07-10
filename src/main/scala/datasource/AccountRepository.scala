package datasource

import domain.member.Id
import util.Dummies

object AccountRepository {
  def allocate(): (Id, Password) = {
    (Dummies.id_signedUp, Password("x"))
  }

}

case class Password(s: String) // todo: here?
