package runner

import api.signup.Request
import datasource.member.MemberRepository
import datasource._Database
import domain.credit_card.CreditCard
import domain.member.{Password, BirthDate, Id, Name}
import util.Dummies

object SignUp extends Errors[Request, (Id, Password)] {
  private def create(name: Name, birthDate: BirthDate, creditCard: CreditCard): Request = Request(
    name,
    Dummies.requestEMail,
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
    _Database.initialize()

    val member1 = request_valid.creator.apply(MemberRepository.allocate._1)
    MemberRepository.signUp(member1)

    val member2 = request_valid.creator.apply(MemberRepository.allocate._1)
    MemberRepository.signUp(member2)

    val member3 = request_valid.creator.apply(MemberRepository.allocate._1)
    MemberRepository.signUp(member3)

    println(MemberRepository.findOneBy(Id("1")))
    println(MemberRepository.findOneBy(Id("2")))
    println(MemberRepository.findOneBy(Id("3")))

    MemberRepository.resignApplication(member2)

    println(MemberRepository.findOneBy(Id("1")))
    println(MemberRepository.findOneBy(Id("2")))
    println(MemberRepository.findOneBy(Id("3")))
  }
}
