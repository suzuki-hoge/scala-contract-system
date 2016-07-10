package datasource

import java.io.File

import datasource.member.MemberRepository

import scala.slick.driver.SQLiteDriver.simple._

object _Database {
  private val DB_PATH = "test.sqlite3"
  private val dbFile = new File(DB_PATH)

  val db = Database.forURL("jdbc:sqlite:" + DB_PATH, driver = "org.sqlite.JDBC")

  private val tables = List(
    MemberRepository.members
  )

  def initialize() = {
    db withSession { implicit session =>
      if (dbFile.exists()) dbFile.delete()

      tables.foreach(_.ddl.create)
    }
  }
}
