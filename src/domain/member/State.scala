package domain.member

import java.time.LocalDateTime

trait State {
  def isContracted: Boolean
  def contracted: ContractedState
}

case class ContractedState(dateTime: ContractedDateTime) extends State {
  val isContracted = true
  val contracted:ContractedState = this
}

case class ContractedDateTime(v: LocalDateTime)

case class ResignAppliedState(before: ContractedState, dateTime: ResignAppliedDateTime) extends State {
  val isContracted = false
  val contracted:ContractedState = before
}

case class ResignAppliedDateTime(v: LocalDateTime)

case class ResignedState(before: ResignAppliedState, dateTime: ResignedDateTime) extends State {
  val isContracted = false
  val contracted:ContractedState = before.before
}

case class ResignedDateTime(v: LocalDateTime)
