package domain.member

import util.Session

case class Member(
                   id: Id,
                   name: Name,
                   mailAddress: EMail,
                   gender: Gender,
                   birthDate: BirthDate,
                   address: Address,
                   contact: Contact,
                   course: Course,
                   state: State
                   ) {
  def resignApplication(): Member = {
    Member(
      id, name, mailAddress, gender, birthDate, address, contact, course,
      ResignAppliedState(state.contracted, ResignAppliedDateTime(Session.now))
    )
  }

  def resignExecution(): Member = {
    Member(
      id, name, mailAddress, gender, birthDate, address, contact, course,
      ResignedState(state.resignApplied(), ResignedDateTime(Session.now))
    )
  }
}
