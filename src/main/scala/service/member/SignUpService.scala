package service.member

import api.member.sign_up.Request
import datasource.account.AccountRepository
import datasource.credit_card.CreditCardRepository
import datasource.member.MemberRepository
import domain.account.Password
import domain.member.Id

object SignUpService {
  def apply(request: Request): (Id, Password) = {
    assertApplicable(request)

    val (id, password) = AccountRepository.allocate
    val member = request.creator.apply(id)

    MemberRepository.signUp(member)
    CreditCardRepository.signUp(id, request.creditCard)
    AccountRepository.signUp(id, password)

    (id, password)
  }

  private def assertApplicable(request: Request): Unit = {
    assert(MemberRepository.findOneBy(request.name).isEmpty, "already signed up")
    assert(request.birthDate.isAdult, "minor is not applicable")
    assert(CreditCardRepository.isValid(request.creditCard), "invalid credit card")
  }
}
