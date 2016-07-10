package domain.member

case class Contact(phoneNumber: Phone, daytimeTelNumber: Daytime)

case class Phone(s: String)

case class Daytime(s: String)
