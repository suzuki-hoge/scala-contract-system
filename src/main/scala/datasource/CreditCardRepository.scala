package datasource

import domain.credit_card.CreditCard
import domain.member.Id
import util.Dummies

object CreditCardRepository {
  def isValid(c: CreditCard): Boolean = {
    c match {
      case Dummies.creditCard_valid => true
      case _ => false
    }
  }

  def signUp(id: Id, creditCard: CreditCard): Unit = {
    // do nothing
  }
}
