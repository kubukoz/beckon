package beckon

import org.scalatest.{Matchers, WordSpec}

class BeckonTests extends WordSpec with Matchers {
  "Summon0" should {
    "summon Show" in {
      trait Show[T] { def show(t: T): String }
      object Show extends Summon0[Show]

      implicit val intShow: Show[Int] = new Show[Int] { def show(s: Int): String = s.toString }

      Show[Int].show(5) shouldBe "5"
    }
  }

  "Summon1" should {
    "summon Functor" in {
      trait Functor[F[_]] { def map[A, B](f: A => B): F[A] => F[B] }
      object Functor extends Summon1[Functor]

      implicit val listFunctor: Functor[List] = new Functor[List] {
        override def map[A, B](f: A => B): List[A] => List[B] = _.map(f)
      }

      Functor[List].map((_: Int) + 1)(List(1, 2, 3)) shouldBe List(2, 3, 4)
    }
  }

  "Summon2" should {
    "summon Bifunctor" in {
      trait Bifunctor[F[_, _]] { def bimap[A, B, C, D](fab: F[A, B])(f: A => C, g: B => D): F[C, D] }
      object Bifunctor extends Summon2[Bifunctor]

      implicit val eitherBifunctor: Bifunctor[Either] = new Bifunctor[Either] {
        override def bimap[A, B, C, D](fab: Either[A, B])(f: A => C, g: B => D): Either[C, D] = fab match {
          case Left(l)  => Left(f(l))
          case Right(r) => Right(g(r))
        }
      }

      Bifunctor[Either].bimap(Left(5): Either[Int, String])(_ - 1, _.reverse) shouldBe Left(4)
      Bifunctor[Either].bimap(Right("Hello"): Either[Int, String])(_ - 1, _.reverse) shouldBe Right("olleH")
    }
  }
}
