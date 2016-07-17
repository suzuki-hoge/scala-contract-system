package service.member

import api.member.sign_up.Request
import datasource.credit_card.CreditCardRepository
import datasource.member.MemberRepository
import domain.member.{Id, Password}

object SignUpService {
    def apply(request: Request): (Id, Password) = {
      assertApplicable(request)

      val (id, password) = MemberRepository.allocate
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
