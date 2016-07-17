package service.member

import java.time.LocalDate

import api.member.sign_up._
import domain.credit_card._
import domain.member._

object SignUpRequest {
  val valid = create(
    1990,
    "1111-2222-3333-4444"
  )

  val invalidBirthDate = create(
    2000,
    "1111-2222-3333-4444"
  )

  val invalidCreditCard = create(
    1990,
    "5555-6666-7777-8888"
  )

  private def create(year: Int, number: String): Request = Request(
    Name(
      Kanji("太郎"),
      Kana("たろう")
    ),
    RequestEMail("taro"),
    Gender("male"),
    BirthDate(LocalDate.of(year, 3, 25)),
    Address(
      ZipCode("140-0001"),
      Street("品川")
    ),
    Contact(
      Phone("090-1111-2222"),
      Daytime("03-1111-2222")
    ),
    CreditCard(
      Number(number),
      ExpirationDate("10-16"),
      SecurityCode("123")
    )
  )
}


