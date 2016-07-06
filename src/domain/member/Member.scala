package domain.member

case class Member(
                   id: Id,
                   name: Name,
                   mailAddress: MailAddress,
                   gender: Gender,
                   birthDate: BirthDate,
                   address: Address,
                   contact: Contact,
                   connectionCourse: ConnectionCourse
                   )
