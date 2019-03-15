package beckon

trait Summon0[A[_]] {
  def apply[T](implicit A: A[T]): A[T] = A
}

trait Summon1[A[_[_]]] {
  def apply[F[_]](implicit A: A[F]): A[F] = A
}

trait Summon2[A[_[_, _]]] {
  def apply[F[_, _]](implicit A: A[F]): A[F] = A
}
