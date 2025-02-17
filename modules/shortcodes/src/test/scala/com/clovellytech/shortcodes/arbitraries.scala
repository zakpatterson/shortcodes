package com.clovellytech.shortcodes

import cats.data.OptionT
import cats.data.NonEmptyVector
import org.scalacheck._
import cats.effect._
import cats.implicits._
import com.clovellytech.shortcode.ShortCode

object arbitraries {

  type WordList = NonEmptyVector[String]

  def wordListGen[F[_]: Sync: ContextShift](blocker: Blocker): OptionT[F, Gen[WordList]] = {
    val r = new ShortCode[F](blocker)

    r.getRandom(100000).map(_._1).map { ws =>
      Gen.nonEmptyListOf(Gen.oneOf(ws.toIterable)).map(_.toVector.toNev.get)
    }
  }

}
