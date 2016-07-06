package domain.member

import java.time.LocalDate

case class BirthDate(v: LocalDate) {
  def isAdult: Boolean = {
    true
  }
}
