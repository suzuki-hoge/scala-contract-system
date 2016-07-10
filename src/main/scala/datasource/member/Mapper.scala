package datasource.member

import domain.member._
import util.Dummies

import scala.slick.driver.SQLiteDriver.simple._

object Mapper {
  type Row = (String, String, String, String, String)

  class _Member(tag: Tag) extends Table[Row](tag, "member") {

    def id = column[String]("id", O.PrimaryKey)

    def kanji = column[String]("kanji")

    def kana = column[String]("kana")

    def course = column[String]("course")

    def state = column[String]("state")

    def * = (id, kana, kanji, course, state)
  }

  object _Member {
    def toRow(member: Member): Row = {
      (member.id.s, member.name.kanji.s, member.name.kana.s, member.course.s, _State.toCol(member.state))
    }

    def toMember(row: Row): Member = {
      Member(
        Id(row._1),
        toName(row),
        Dummies.eMail,
        Dummies.gender,
        Dummies.birthDate,
        Dummies.address,
        Dummies.contact,
        Course(row._4),
        _State.toState(row._5)
      )
    }

    def toId(row: Row): Id = Id(row._1)

    def toName(row: Row): Name = Name(Kanji(row._2), Kana(row._3))
  }

  object _State {
    def toCol(state: State): String = {
      (state.isContracted, state.isResignApplied) match {
        case (true, false) => "contracted"
        case (false, true) => "resign applied"
        case _ => "resigned"
      }
    }

    def toState(col: String): State = {
      col match {
        case "contracted" => Dummies.state_contracted
        case "resign applied" => Dummies.state_resignApplied
        case _ => Dummies.state_resigned
      }
    }
  }

}
