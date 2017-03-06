package org.yargonaut.internal

import scalaz.Endo

/**
  * State of an ongoing parse
  */
final case class ParseState(currentLevel: LevelState, cursor: InputCursor) {
  def consume(f: Endo[LevelState])(s: String) =
    ParseState(f(currentLevel), cursor.add(s))
  val consumeI = consume(Endo.idEndo[LevelState]) _
}
