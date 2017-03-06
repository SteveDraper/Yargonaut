package org.yargonaut.internal

import argonaut.Json

/**
  * State representing progress of parsing a level of nested elements
  */
sealed trait LevelState

case object RootLevelState extends LevelState
final case class PartialLevelState(soFar: Option[Json], parent: LevelState) extends LevelState
final case class CompleteLevelState(parsed: Json, parent: LevelState) extends LevelState
final case class FailedLevelState(at: InputCursor, reason: String, parent: LevelState) extends LevelState
