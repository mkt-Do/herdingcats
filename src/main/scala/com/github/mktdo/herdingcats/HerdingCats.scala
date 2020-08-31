package com.github.mktdo.herdingcats

import simulacrum._

@typeclass trait Monoid[A] {
  @op("|+|") def mappend(a1: A, a2: A): A
  def mzero: A
}

object Monoid {
  implicit val IntMonoid: Monoid[Int] = new Monoid[Int] {
    def mappend(a1: Int, a2: Int): Int = a1 + a2
    def mzero: Int = 0
  }
  implicit val StringMonoid: Monoid[String] = new Monoid[String] {
    def mappend(a1: String, a2: String): String = a1 + a2
    def mzero: String = ""
  }
}

trait FoldLeft[F[_]] {
  def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B): B
}

object FoldLeft {
  implicit val FoldLeftList: FoldLeft[List] = new FoldLeft[List] {
    def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B): B = xs.foldLeft(b)(f)
  }
}

object HerdingCats {

  def sum[M[_]: FoldLeft, A: Monoid](xs: List[A]): A = {
    val m = implicitly[Monoid[A]]
    val fl = implicitly[FoldLeft[M]]
    fl.foldLeft(xs, m.mzero, m.mappend)
  }
}
