package runner

import api.signup.Request
import datasource.{Dummies, Password}
import domain.credit_card.CreditCard
import domain.member.{BirthDate, Id, Name}
import service.member.SignUpService

object SignUp extends Errors[Request, (Id, Password)] {
  private def create(name: Name, birthDate: BirthDate, creditCard: CreditCard): Request = Request(
    name,
    Dummies.requestMailAddress,
    Dummies.gender,
    birthDate,
    Dummies.address,
    Dummies.contact,
    creditCard
  )

  val request_valid = create(
    Dummies.name_nonMember,
    Dummies.birthDate_valid,
    Dummies.creditCard_valid
  )

  val request_invalid_name = create(
    Dummies.name_signedUp,
    Dummies.birthDate_valid,
    Dummies.creditCard_valid
  )

  val request_invalid_birthDate = create(
    Dummies.name_nonMember,
    Dummies.birthDate_invalid,
    Dummies.creditCard_valid
  )

  val request_invalid_creditCard = create(
    Dummies.name_nonMember,
    Dummies.birthDate_valid,
    Dummies.creditCard_invalid
  )

  def main(args: Array[String]): Unit = {
    val (id, password) = SignUpService.apply(request_valid)
    println(id, password)

    errors(
      List(
        (request_invalid_name, "already signed up"),
        (request_invalid_birthDate, "minor is not applicable"),
        (request_invalid_creditCard, "invalid credit card")
      ),
      SignUpService.apply
    )
  }
}
