package domain.member

import api.signup.RequestMailAddress

case class MailAddress(s: String)

case object MailAddress {
  def create(v: RequestMailAddress) = {
    MailAddress(v.s)
  }
}

