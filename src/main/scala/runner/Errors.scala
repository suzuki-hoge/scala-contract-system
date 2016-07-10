package runner

import datasource._Database

trait Errors[IN, OUT] {
  def errors(patterns: List[(IN, String)], f: IN => OUT): Unit = {
    _Database.initialize()

    patterns.foreach(
      pattern => try {
        f.apply(pattern._1)
      } catch {
        case e: AssertionError => assert(e.getMessage.contains(pattern._2))
      }
    )

    println("expected errors")
  }
}

