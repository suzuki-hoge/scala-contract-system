package service.member

import java.time.LocalDate

import api.member.sign_up._
import domain.credit_card._
import domain.member._

object SignUpRequest {
  val basic = create(
    1990,
    "1111-2222-3333-4444",
    Course.basic
  )

  val niconico = create(
    1990,
    "1111-2222-3333-4444",
    Course.niconico,
    "次郎",
    "じろう"
  )

  val invalidBirthDate = create(
    2000,
    "1111-2222-3333-4444",
    Course.basic
  )

  val invalidCreditCard = create(
    1990,
    "5555-6666-7777-8888",
    Course.basic
  )

  private def create(
                      year: Int,
                      number: String,
                      course: Course,
                      kanji: String = "太郎",
                      kana: String = "たろう"
                    ): Request = Request(
    Name(
      Kanji(kanji),
      Kana(kana)
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
    course,
    CreditCard(
      Number(number),
      ExpirationDate("10-16"),
      SecurityCode("123")
    )
  )
}


