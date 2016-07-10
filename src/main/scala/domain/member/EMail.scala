package domain.member

import api.signup.RequestEMail

case class EMail(s: String)

case object EMail {
  def create(v: RequestEMail) = {
    EMail(
      "%s@xxx.com".format(v)
    )
  }
}

