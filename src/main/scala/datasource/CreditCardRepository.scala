package datasource

import domain.credit_card.CreditCard
import domain.member.Id

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
