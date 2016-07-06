package domain.member

import java.time.LocalDateTime

trait State {
  def isContracted: Boolean
}

case class ContractedState(dateTime: ContractedDateTime) extends State {
  val isContracted = true
}

case class ContractedDateTime(v: LocalDateTime)

case class ResignAppliedState(before: ContractedState, dateTime: ResignAppliedDateTime) extends State {
  val isContracted = false
}

case class ResignAppliedDateTime(v: LocalDateTime)

case class ResignedState(before: ResignAppliedState, dateTime: ResignedDateTime) extends State {
  val isContracted = false
}

case class ResignedDateTime(v: LocalDateTime)
