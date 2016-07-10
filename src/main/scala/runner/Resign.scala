package runner

import domain.member.{Password, Id, Member}
import service.member.ResignService
import util.Dummies

object Resign extends Errors[(Id, Password), Member] {
//  def main(args: Array[String]): Unit = {
//    refer()
//    apply()
//    execute()
//  }
//
//  private def refer(): Unit = {
//    val member = ResignService.refer(Dummies.id_signedUp, Dummies.password_valid)
//    println(member.name)
//
//    errors(
//      List(
//        ((Dummies.id_nonMember, Dummies.password_valid), "invalid id or password"),
//        ((Dummies.id_resignApplied, Dummies.password_valid), "invalid state for resign"),
//        ((Dummies.id_resigned, Dummies.password_valid), "invalid state for resign")
//      ),
//      t => ResignService.refer(t._1, t._2)
//    )
//  }
//
//  private def apply(): Unit = {
//    ResignService.apply(Dummies.id_signedUp, Dummies.password_valid)
//    println("success")
//
//    errors(
//      List(
//        ((Dummies.id_nonMember, Dummies.password_valid), "invalid id or password"),
//        ((Dummies.id_resignApplied, Dummies.password_valid), "invalid state for resign"),
//        ((Dummies.id_resigned, Dummies.password_valid), "invalid state for resign")
//      ),
//      t => ResignService.refer(t._1, t._2)
//    )
//  }
//
//  private def execute(): Unit = {
//    ResignService.execute(Dummies.id_resignApplied)
//    println("success")
//  }
}
