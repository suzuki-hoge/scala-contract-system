package domain.member

case class ConnectionCourse(s: String)

case object ConnectionCourse {
  def basic: ConnectionCourse = ConnectionCourse("basic")
}

