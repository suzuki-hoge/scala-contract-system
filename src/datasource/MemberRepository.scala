package datasource

import domain.member.{Id, Member, Name}

object MemberRepository {
  def findOneBy(name: Name): Option[Member] = {
    name match {
      case Dummies.name_signedUp => Option(Dummies.member_signedUp)
      case _ => Option.empty
    }
  }

  def findOneBy(id: Id): Option[Member] = {
    id match {
      case Dummies.id_signedUp => Option(Dummies.member_signedUp)
      case Dummies.id_resignApplied => Option(Dummies.member_resignApplied)
      case Dummies.id_resigned => Option(Dummies.member_resigned)
      case _ => Option.empty
    }
  }

  def findOneBy(id: Id, sup: Id => RuntimeException): Member = {
    findOneBy(id) match {
      case Some(x) => x
      case None => throw sup.apply(id)
    }
  }

  def signUp(member: Member): Unit = {
    // do nothing
  }

  def resignApplication(member: Member): Member = {
    // do nothing
    member
  }

  def resignExecution(member: Member): Unit = {
    // do nothing
  }
}
