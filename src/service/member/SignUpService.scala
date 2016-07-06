package service.member

import api.signup.Request
import datasource.{AccountRepository, CreditCardRepository, MemberRepository, Password}
import domain.member.Id

object SignUpService {
  def apply(request: Request): (Id, Password) = {
    assertApplicable(request)

    val (id, password) = AccountRepository.allocate()
    val member = request.creator.apply(id)

    MemberRepository.signUp(member)
    CreditCardRepository.signUp(id, request.creditCard)

    (id, password)
  }

  private def assertApplicable(request: Request): Unit = {
    assert(MemberRepository.findOneBy(request.name).isEmpty, "already signed up")
    assert(request.birthDate.isAdult, "minor is not applicable")
    assert(CreditCardRepository.isValid(request.creditCard), "invalid credit card")
  }
}
