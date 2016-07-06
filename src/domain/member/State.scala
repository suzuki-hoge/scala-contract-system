package domain.member

import java.time.LocalDateTime

trait State

case class ContractedState(dateTime: ContractedDateTime) extends State

case class ContractedDateTime(v: LocalDateTime)

case class ResignAppliedState(before: ContractedState, dateTime: ResignAppliedDateTime) extends State

case class ResignAppliedDateTime(v: LocalDateTime)

case class ResignedState(before: ResignAppliedDateTime, dateTime: ResignedDateTime) extends State

case class ResignedDateTime(v: LocalDateTime)
