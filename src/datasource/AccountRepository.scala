package datasource

import domain.member.Id

object AccountRepository {
  def allocate(): (Id, Password) = {
    (Dummies.id_signedUp, Password("x"))
  }

}

case class Password(s: String) // todo here?
