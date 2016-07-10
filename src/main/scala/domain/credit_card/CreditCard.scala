package domain.credit_card

case class CreditCard(number: Number, expirationDate: ExpirationDate, securityCode: SecurityCode)

case class Number(s: String)

case class ExpirationDate(s: String)

case class SecurityCode(s: String)

