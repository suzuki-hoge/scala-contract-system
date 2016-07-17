package domain.member

import java.time.LocalDateTime

import util.Session

case class State(signUp: SignUp, resign: Option[Resign]) {
  def isResignApplied: Boolean = resign.exists(_.isApplied)

  def isResignExecuted: Boolean = resign.exists(!_.isApplied)

  def resignApply: State = State(
    signUp,
    signUp.resignApply
  )

  def resignExecute = State(
    signUp,
    resign.map(_.execute)
  )
}

case object State {
  def signUpApply: State = State(
    SignUpApplied(SignUpAppliedDateTime(Session.systemReceiptTime)),
    None
  )
}

trait SignUp {
  def resignApply: Option[ResignApplied]
}

case class SignUpApplied(dateTime: SignUpAppliedDateTime) extends SignUp {
  def resignApply = Some(ResignApplied(ResignAppliedDateTime(Session.systemReceiptTime)))
}

case class SignUpAppliedDateTime(v: LocalDateTime)


trait Resign {
  def isApplied: Boolean

  def execute: ResignExecuted
}

case class ResignApplied(dateTime: ResignAppliedDateTime) extends Resign {
  def isApplied = true

  def execute = ResignExecuted(
    this,
    ResignExecutedDateTime(Session.systemReceiptTime)
  )
}

case class ResignAppliedDateTime(v: LocalDateTime)

case class ResignExecuted(before: ResignApplied, dateTime: ResignExecutedDateTime) extends Resign {
  def isApplied = false

  def execute = this
}

case class ResignExecutedDateTime(v: LocalDateTime)
