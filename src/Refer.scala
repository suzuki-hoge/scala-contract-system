import datasource.Dummies
import service.member.{ReferService, SignUpService}

object Refer {
  def main(args: Array[String]): Unit = {
    val member = ReferService.referForResign(Dummies.id_signedUp, Dummies.password_valid)
    println(member.name)

    List(
      ((Dummies.id_nonMember, Dummies.password_valid), "invalid id or password"),
      ((Dummies.id_resignApplied, Dummies.password_valid), "invalid state for resign"),
      ((Dummies.id_resigned, Dummies.password_valid), "invalid state for resign")
    ).foreach(
        t => try {
          ReferService.referForResign(t._1._1, t._1._2)
        } catch {
          case e: AssertionError => assert(e.getMessage.contains(t._2))
        }
      )

    println("expected errors")
  }
}
