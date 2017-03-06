package org.yargonaut.internal

final case class InputCursor(charNum: Int, remaining: String) {
  def add(s: String) =
    InputCursor(
      charNum + s.length,
      remaining.substring(s.length)
    )
}
