package datasource

import domain.member.{Member, Name}

object MemberRepository {
  def find(name: Name): Option[Member] = {
    name match {
      case Dummies.name_signedUp => Option(Dummies.member_signedUp)
      case _ => Option.empty
    }
  }

  def signUp(member: Member): Unit = {
    // do nothing
  }
}
