package datasource.account

import domain.member._

import scala.slick.driver.SQLiteDriver.simple._

object Mapper {

  type Row = (String, String)

  class _Account(tag: Tag) extends Table[Row](tag, "account") {
    def id = column[String]("id", O.PrimaryKey)

    def password = column[String]("password")

    def * = (id, password)
  }

  object _Account {
    def toRow(id: Id, password: Password): Row = {
      (id.s, password.s)
    }
  }

}
