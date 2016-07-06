package datasource

import java.time.LocalDate

import api.signup.RequestMailAddress
import core.Session
import domain.credit_card.{CreditCard, ExpirationDate, Number, SecurityCode}
import domain.member._

object Dummies {
  val id_signedUp = Id("1")

  val name_nonMember = Name(
    Kanji("非会員"),
    Kana("ひかいいん")
  )

  val name_signedUp = Name(
    Kanji("入会済み"),
    Kana("にゅうかいずみ")
  )

  val requestMailAddress = RequestMailAddress("biglobe")

  val mailAddress = MailAddress.create(requestMailAddress)

  val gender = Gender("male")

  val birthDate_valid = BirthDate(LocalDate.of(1990, 3, 25))

  val birthDate_invalid = BirthDate(LocalDate.of(2000, 3, 25))

  val address = Address(
    ZipCode("140-0001"),
    Street("品川")
  )

  val contact = Contact(
    PhoneNumber("090-1111-2222"),
    DaytimeTelNumber("03-1111-2222")
  )

  val state_contracted = ContractedState(ContractedDateTime(Session.now))

  val member_signedUp = Member(
    id_signedUp,
    name_signedUp,
    mailAddress,
    gender,
    birthDate_valid,
    address,
    contact,
    ConnectionCourse.basic,
    state_contracted
  )

  val creditCard_valid = CreditCard(
    Number("1111-2222-3333-4444"),
    ExpirationDate("1016"),
    SecurityCode("123")
  )

  val creditCard_invalid = CreditCard(
    Number("5555-6666-7777-8888"),
    ExpirationDate("1016"),
    SecurityCode("123")
  )
}
