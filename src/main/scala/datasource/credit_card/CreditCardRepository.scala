package datasource.credit_card

import datasource._Database
import datasource.credit_card.Mapper.{_CreditCard, _Valid}
import domain.credit_card.CreditCard
import domain.member.Id

import scala.slick.driver.SQLiteDriver.simple._

object CreditCardRepository {

  val creditCards = TableQuery[_CreditCard]
  val validCreditCards = TableQuery[_Valid]

  // outer system mock

  def isValid(creditCard: CreditCard): Boolean = {
    _Database.db withSession { implicit session =>
      validCreditCards.filter(_.number === creditCard.number.s).list match {
        case Nil => false
        case _ => true
      }
    }
  }

  // insert

  def signUp(id: Id, creditCard: CreditCard): Unit = {
    _Database.db withSession { implicit session =>
      creditCards += _CreditCard.toRow(id, creditCard)
    }
  }
}
