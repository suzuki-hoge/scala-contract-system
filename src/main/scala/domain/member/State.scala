package domain.member

import java.time.LocalDateTime

trait State {
  def isContracted: Boolean

  def contracted: ContractedState

  def isResignApplied: Boolean

  def resignApplied: () => ResignAppliedState
}

case class ContractedState(dateTime: ContractedDateTime) extends State {
  val isContracted = true
  val contracted: ContractedState = this
  val isResignApplied = false
  val resignApplied: () => ResignAppliedState = () => throw new RuntimeException(
    "contracted state has not resign applied state"
  )
}

case class ContractedDateTime(v: LocalDateTime)

case class ResignAppliedState(before: ContractedState, dateTime: ResignAppliedDateTime) extends State {
  val isContracted = false
  val contracted: ContractedState = before
  val isResignApplied = true
  val resignApplied: () => ResignAppliedState = () => this
}

case class ResignAppliedDateTime(v: LocalDateTime)

case class ResignedState(before: ResignAppliedState, dateTime: ResignedDateTime) extends State {
  val isContracted = false
  val contracted: ContractedState = before.before
  val isResignApplied = false
  val resignApplied: () => ResignAppliedState = () => before
}

case class ResignedDateTime(v: LocalDateTime)
