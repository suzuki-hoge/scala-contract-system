package datasource.member

import datasource._Database
import datasource.member.Mapper.{Row, _Member}
import domain.account.Password
import domain.member._

import scala.slick.driver.SQLiteDriver.simple._

object MemberRepository {

  val members = TableQuery[_Member]

  // select

  def findOneBy(name: Name): Option[Member] = {
    findOne(_Member.toName(_) == name)
  }

  def findOneBy(id: Id): Option[Member] = {
    findOne(_Member.toId(_) == id)
  }

  def findOneBy(id: Id, supplier: Id => RuntimeException): Member = {
    findOneBy(id) match {
      case Some(x) => x
      case None => throw supplier.apply(id)
    }
  }

  private def findOne(predicate: Row => Boolean): Option[Member] = {
    _Database.db withSession { implicit session =>
      members.list.find(predicate).map(_Member.toMember)
    }
  }

  def resignApplied(): List[Member] = {
    _Database.db withSession { implicit session =>
      members.filter(_.state === "resign applied").list.map(_Member.toMember)
    }
  }

  // insert

  def signUp(member: Member): Unit = {
    _Database.db withSession { implicit session =>
      members += _Member.toRow(member)
    }
  }

  // update

  def save(member: Member): Unit = {
    _Database.db withSession { implicit session =>
      members.filter(_.id === member.id.s).update(_Member.toRow(member))
    }
  }
}
