package api.signup

import domain.credit_card.CreditCard
import domain.member._

case class Request(
                    name: Name,
                    requestMailAddress: RequestMailAddress,
                    gender: Gender,
                    birthDate: BirthDate,
                    address: Address,
                    contact: Contact,
                    creditCard: CreditCard
                    ) {
  def creator: Id => Member = {
    id => Member(
      id, name, MailAddress.create(requestMailAddress), gender, birthDate, address, contact, ConnectionCourse.basic
    )
  }
}
