package org.yargonaut.internal

import scala.util.matching.Regex

/**
  * Created by sdraper on 3/5/17.
  */
object Parsers {
  private def regex(r: Regex): Parser[String] =
    parser { s =>
      r.findPrefixMatchOf(s.cursor.remaining)
        .fold(
          (s, parseFailed[String](s.cursor, s"No match found for '${r.toString}'")))(
          m => (s.consumeI(m.matched), parsedOk(m.matched)))
    }

  val indentation: Parser[Indentation] =
    regex(" *".r).map(s => Indentation(s.length))
}
