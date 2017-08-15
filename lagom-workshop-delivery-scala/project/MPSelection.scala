package stbstudent

import sbt._
import sbt.complete.DefaultParsers.{IntBasic, Space}
import sbtstudent.SharedCode

object MPSelection {
  private lazy val ExerciseNr = Space ~> IntBasic.?

  def setActiveExerciseNr: Command = Command("setActiveExerciseNr")(s => ExerciseNr) { (state, arg) =>
    if (arg.isDefined) SharedCode.activateExerciseNr(state, arg.get) else state

  }

  def activateAllExercises: Command = Command.command("activateAllExercises") { state =>
    SharedCode.activateAllExercises(state)
  }
}