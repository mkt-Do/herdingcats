package com.github.mktdo.herdingcats

import com.github.mktdo.herdingcats.HerdingCats.sum
import com.github.mktdo.herdingcats.{ FoldLeft, Monoid }
import org.scalatest.flatspec.AnyFlatSpec

class HerdingCatsSpec extends AnyFlatSpec {

  "Using sum method againt Int List" should "sum up all elements" in {
    val l: List[Int] = List(1, 2, 3, 4)
    assert(sum(l) == 10)
  }

  "Using sum method against String List" should "concatenate all elements" in {
    val l: List[String] = List("a", "b", "c", "d")
    assert(sum(l) == "abcd")
  }

  "Using sum method against Int List with multiMonoid" should "multiple all elements" in {
    val l: List[Int] = List(1, 2, 3, 4)
    val multiMonoid: Monoid[Int] = new Monoid[Int] {
      def mappend(a1: Int, a2: Int): Int = a1 * a2
      def mzero: Int = 1
    }
    val fl: FoldLeft[List] = new FoldLeft[List] {
      def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B): B = xs.foldLeft(b)(f)
    }
    assert(sum(l)(fl, multiMonoid) == 24)
  }
}
