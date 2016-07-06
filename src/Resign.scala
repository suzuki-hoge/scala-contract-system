import datasource.Dummies
import service.member.{ResignService, SignUpService}

object Resign {
  def main(args: Array[String]): Unit = {
    refer()
    apply()
  }

  private def refer(): Unit = {
    val member = ResignService.refer(Dummies.id_signedUp, Dummies.password_valid)
    println(member.name)

    List(
      ((Dummies.id_nonMember, Dummies.password_valid), "invalid id or password"),
      ((Dummies.id_resignApplied, Dummies.password_valid), "invalid state for resign"),
      ((Dummies.id_resigned, Dummies.password_valid), "invalid state for resign")
    ).foreach(
        t => try {
          ResignService.refer(t._1._1, t._1._2)
        } catch {
          case e: AssertionError => assert(e.getMessage.contains(t._2))
        }
      )

    println("expected errors")
  }

  private def apply(): Unit = {
    ResignService.apply(Dummies.id_signedUp, Dummies.password_valid)
    println("success")

    List(
      ((Dummies.id_nonMember, Dummies.password_valid), "invalid id or password"),
      ((Dummies.id_resignApplied, Dummies.password_valid), "invalid state for resign"),
      ((Dummies.id_resigned, Dummies.password_valid), "invalid state for resign")
    ).foreach(
        t => try {
          ResignService.refer(t._1._1, t._1._2)
        } catch {
          case e: AssertionError => assert(e.getMessage.contains(t._2))
        }
      )

    println("expected errors")
  }
}
