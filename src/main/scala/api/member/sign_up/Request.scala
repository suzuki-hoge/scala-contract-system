package api.member.sign_up

import domain.credit_card.CreditCard
import domain.member._

case class Request(
                    name: Name,
                    requestMailAddress: RequestEMail,
                    gender: Gender,
                    birthDate: BirthDate,
                    address: Address,
                    contact: Contact,
                    course: Course,
                    creditCard: CreditCard
                  ) {
  def creator: Id => Member = {
    id => Member(
      id, name, EMail.create(requestMailAddress), gender, birthDate, address, contact, course, State.signUpApply
    )
  }
}
