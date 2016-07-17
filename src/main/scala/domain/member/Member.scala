package domain.member

import java.time.LocalDate

import api.member.sign_up.RequestEMail

case class Member(
                   id: Id,
                   name: Name,
                   eMail: EMail,
                   gender: Gender,
                   birthDate: BirthDate,
                   address: Address,
                   contact: Contact,
                   course: Course,
                   state: State
                 ) {
  def resignApplication(): Member = {
    Member(
      id, name, eMail, gender, birthDate, address, contact, course, state.resignApply
    )
  }

  def resignExecution(): Member = {
    Member(
      id, name, eMail, gender, birthDate, address, contact, course, state.resignExecute
    )
  }
}

// id
case class Id(s: String)

// name
case class Name(kanji: Kanji, kana: Kana)

case class Kanji(s: String)

case class Kana(s: String)

// e mail
case class EMail(s: String)

case object EMail {
  def create(v: RequestEMail) = {
    EMail(
      "%s@xxx.com".format(v)
    )
  }
}

// gender
case class Gender(s: String)

// birth date
case class BirthDate(v: LocalDate) {
  def isAdult: Boolean = {
    LocalDate.now().getYear - v.getYear > 19
  }
}

// address
case class Address(zipCode: ZipCode, street: Street)

case class ZipCode(s: String)

case class Street(s: String)

// contact
case class Contact(phoneNumber: Phone, daytimeTelNumber: Daytime)

case class Phone(s: String)

case class Daytime(s: String)

// course
case class Course(s: String)

case object Course {
  def basic: Course = Course("basic")
}

