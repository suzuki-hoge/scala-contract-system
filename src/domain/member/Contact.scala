package domain.member

case class Contact(phoneNumber: PhoneNumber, daytimeTelNumber: DaytimeTelNumber)

case class PhoneNumber(s: String)

case class DaytimeTelNumber(s: String)
