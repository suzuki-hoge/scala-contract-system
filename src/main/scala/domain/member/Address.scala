package domain.member

case class Address(zipCode: ZipCode, street: Street)

case class ZipCode(s: String)

case class Street(s: String)
