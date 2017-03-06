package org.yargonaut

import scalaz.{@@, EitherT, MonadTrans, State, Tag, \/}
import scalaz.syntax.either._
import scalaz.syntax.monad._

package object internal {
  type ParseResult[A] = ParseFailure \/ A
  // Parsing is modelled as transitions on a parsing state
  def parseFailed[A](at: InputCursor, reason: String): ParseResult[A] =
    ParseFailure(at, reason).left[A]
  def parsedOk[A](a: A): ParseResult[A] =
    a.right

  type StateP[A] = State[ParseState,A]
  type ET[F[_],A] = EitherT[F,ParseFailure,A]
  type Parser[A] = ET[StateP,A]
  def parser[A](sp: StateP[ParseResult[A]]): Parser[A] =
    EitherT(sp)
  def parser[A](st: ParseState => (ParseState,ParseResult[A])): Parser[A] =
    parser(State[ParseState,ParseResult[A]](st))

  def liftE[A](sp: ParseResult[A]) =
    parser(sp.point[StateP])
  def liftS[A](s: StateP[A]) =
    MonadTrans[ET].liftM(s)

  sealed trait IndentationTag
  type Indentation = Int @@ IndentationTag
  def Indentation(n: Int) = Tag[Int, IndentationTag](n)

}
