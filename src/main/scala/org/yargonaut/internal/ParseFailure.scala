package org.yargonaut.internal

final case class ParseFailure(at: InputCursor, reason: String)
