package datasource.credit_card

import domain.credit_card.CreditCard
import domain.member._

import scala.slick.driver.SQLiteDriver.simple._

object Mapper {

  type Row = (String, String)

  class _CreditCard(tag: Tag) extends Table[Row](tag, "credit_card") {
    def id = column[String]("id", O.PrimaryKey)

    def number = column[String]("number")

    def * = (id, number)
  }

  object _CreditCard {
    def toRow(id: Id, creditCard: CreditCard): Row = {
      (id.s, creditCard.number.s)
    }
  }

  class _Valid(tag: Tag) extends Table[String](tag, "valid_credit_card") {
    def number = column[String]("number")

    def * = number
  }
}
