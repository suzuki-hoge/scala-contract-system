package domain.member

import core.Session

case class Member(
                   id: Id,
                   name: Name,
                   mailAddress: MailAddress,
                   gender: Gender,
                   birthDate: BirthDate,
                   address: Address,
                   contact: Contact,
                   connectionCourse: ConnectionCourse,
                   state: State
                   ) {
  def resignApplication(): Member = {
    Member(
      id, name, mailAddress, gender, birthDate, address, contact, connectionCourse,
      ResignAppliedState(state.contracted, ResignAppliedDateTime(Session.now))
    )
  }

  def resignExecution(): Member = {
    Member(
      id, name, mailAddress, gender, birthDate, address, contact, connectionCourse,
      ResignedState(state.resignApplied, ResignedDateTime(Session.now))
    )
  }
}
