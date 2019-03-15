# beckon

Boring library for summoning implicits

[![License](http://img.shields.io/:license-Apache%202-green.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)

## What is this nonsense?

beckon provides traits for some common shapes of type parameterized implicits in Scala. Could be useful for tagless final style.

Example usage of one of the traits, `Summon1`:

```scala
import beckon._

trait Store[F[_]] { def store(s: Thing): F[Unit] }
object Store extends Summon1[Store]

implicit val listStore: Store[IO] = ...

Store[IO].store(thing) //IO[Unit]
```

## Installing

To be honest, it's not even worth publishing an artifact for 9 lines of code,
so just copy/paste the things you're interested in (the source lives in `src/main/scala/beckon`). Enjoy!

## Possibly more sane alternatives

See [cats-tagless](https://github.com/typelevel/cats-tagless). They have an annotation for that.

## FAQ

Q: Why not a single trait for all possible shapes?

A: kind polymorphism isn't a thing in Scala 2.x :(
