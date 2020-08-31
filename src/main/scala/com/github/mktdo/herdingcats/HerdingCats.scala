package com.github.mktdo.herdingcats

trait Monoid[A] {
  def mappend(a1: A, a2: A): A
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

object HerdingCats {

  def sum[A: Monoid](xs: List[A]): A = {
    val m = implicitly[Monoid[A]]
    xs.foldLeft(m.mzero)(m.mappend)
  }
}
