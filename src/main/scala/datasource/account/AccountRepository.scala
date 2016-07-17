package datasource.account

import datasource._Database
import datasource.account.Mapper._Account
import domain.account.Password
import domain.member.Id

import scala.slick.driver.SQLiteDriver.simple._

object AccountRepository {

  val accounts = TableQuery[_Account]

  // allocate

  def allocate: (Id, Password) = {
    val n = _Database.db withSession { implicit session =>
      accounts.list.map(_._1) match {
        case Nil => 1
        case xs => xs.last.toInt + 1
      }
    }
    (Id(n.toString), Password("ps_%s".format(n)))
  }

  // select

  def isExists(id: Id, password: Password): Boolean = {
    _Database.db withSession { implicit session =>
      accounts.filter(_.id === id.s).filter(_.password === password.s).list.size == 1
    }
  }

  // insert

  def signUp(id: Id, password: Password): Unit = {
    _Database.db withSession { implicit session =>
      accounts += _Account.toRow(id, password)
    }
  }
}
