package datasource.account

import datasource._Database
import datasource.account.Mapper.{Row, _Account}
import domain.member.{Password, Id}

import scala.slick.driver.SQLiteDriver.simple._

object AccountRepository {

  val accounts = TableQuery[_Account]

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
