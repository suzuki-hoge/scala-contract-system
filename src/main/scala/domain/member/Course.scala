package domain.member

case class Course(s: String)

case object Course {
  def basic: Course = Course("basic")
}

