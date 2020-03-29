Short codes for Scala
===

This project uses a dictionary of words to generate random short sequences. These are intended for generating site invitation codes, but could probably be used for other purposes.

For now this is only using the [bip-39 word list](https://github.com/bitcoin/bips/blob/master/bip-0039/english.txt). The dictionary has 2048 words, and the sequence generator chooses with replacement, so the randomness for length n is 2048<sup>n</sup>. So there are 1.8 x 10<sup>13</sup> combinations of 4 words for example. A string of n words will have about the same randomness as an alphanumeric string of length 2n.

Example
---
```scala
scala> val sc = new ShortCode[IO](blocker)
sc: com.clovellytech.shortcode.ShortCode[cats.effect.IO] = com.clovellytech.shortcode.ShortCode@2f738d7f

scala> sc.getRandom(4).value.unsafeRunSync.get
res0: (cats.data.NonEmptyVector[String], Array[Byte]) = (NonEmptyVector(join, jewel, practice, mixture, inhale),Array(3, -63, 3, -65, 5, 75, 4, 114, 3, -99))

scala> sc.bytesToWords(res0._2).value.unsafeRunSync().get
res1: cats.data.NonEmptyVector[String] = NonEmptyVector(join, jewel, practice, mixture, inhale)
```