package com.pierangeloc.scala

import org.scalatest.{ShouldMatchers, FunSpec}

/**
 * Created by pierangeloc on 24-8-14.
 */
class AssertionsPlayground extends FunSpec with ShouldMatchers {

  describe("simple matchers") {
    it("should assert correctly on equality of base types") {
      val list:List[Int] = List(1, 2, 3, 4)
      list.length should be(4)

      "Voodoo child" should be ("Voodoo child")
    }

    it("should assert correctly on String with regex") {
      val divinaIncipit = """Nel mezzo del cammin di nostra vita
        |mi ritrovai per una selva oscura
        |che la diritta via era smarrita"""
      divinaIncipit should startWith("Nel")
      divinaIncipit should endWith("rrita")
      divinaIncipit should startWith regex("""N[el]{2}.*""")
    }

    it("should assert using relational operators") {
      val i = 10
      i should be < 100
      i should be > 5
      i should be <= 10
      i should be >= 10

    }

    it("should assert numbers with range")  {
      val delta = 0.9 - 0.8
      delta should be (0.1 +- 0.01)

      val outOfRange = 100
      outOfRange should not be (80 +- 10)
    }

    it("should allow identifiying instances properly") {
      val l1 = List(1,2,3,4,5)
      val l2 = List(1,2,3,4,5)
      val l3 = l1

      l1 should be === l2

      l1 should be theSameInstanceAs(l3)
    }

    it("should handle properly iterable, List, Seq") {
      val l1 = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

      l1 should contain (3)

      //length and size are same
      l1 should have length(10)
      l1 should have size(10)
    }


    it("should handle properly maps (and functions???)") {
      val map = Map("Mark Knopfler" -> "Dire Straits", "Eric Clapton" -> "Cream")
      info("created knopfler/clapton map")
      map should contain key("Mark Knopfler")
      map should contain value ("Cream")
    }

    it("should evaluate consistently compound assertions, with and/or") {
      val list = List(1,3,5,7,9,2)

      list should (contain(3) and contain (5) and (not contain(4)))

      //and/or are not short circuited
      var i = 0
      list should (contain({i+= 1; 1}) or contain({i+=1; 3}))
      i should be (2)
    }

    it("should calculate Riemann integral in a fancy way") {
      pending
    }

    ignore("should prove that 1 + 1 == 2"){
      (1 + 1) should be(2)
    }

  }


}
