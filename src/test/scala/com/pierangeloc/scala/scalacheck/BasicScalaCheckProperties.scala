package com.pierangeloc.scala.scalacheck


import org.scalacheck.{Gen, Properties, Prop}
import org.scalacheck.Prop._
/**
 * Created by pierangeloc on 30-8-14.
 */
object BasicScalaCheckProperties extends Properties("Simple Math") {

  property("Sum is greater than or equal than its addends (avoiding overflows!!!)") = forAll {
    (x: Int, y: Int) => (x >=0 && y >= 0)  ==>
    classify((x >=0 && y >= 0), "all set")
    {
//      println(s"$x, $y")
      x + y.toLong >= x && x + y.toLong >= y
    }
  }

  property("Appending 2 strings gives a string whose length is equal to the sum of lengths") = forAll {
    (s1: String, s2: String, s3: Option[String]) => {
      s3 match {
        case Some(ss3) =>
          (s1 + s2 + ss3).length == s1.length + s2.length + ss3.length
        case _ =>
          (s1 + s2).length == s1.length + s2.length
      }
    }
  }

  val commutativity = forAll {
    (x: Int, y: Int) => x + y == y + x
  }

  val associativity = forAll {
    (x: Int, y: Int, z: Int) => (x + y) + z == x + (y + z)
  }

  property("plus is associative and commutative") = commutativity && associativity

  property("constant generator") = forAll(Gen.const("constant")) {_ == "constant"}

  property("numbers between 1 an 100") = forAll(Gen.choose(1, 100)) {x => x >= 1 && x <= 100}

  property ("One of other generators is consistently checked") = forAll(Gen.oneOf(Gen.choose(0, 4), Gen.choose(5, 9))) {
    x => x >= 0 && x <= 9
  }

  property ("One of other generators is consistently checked, also with inhomogeneous types") = forAll(Gen.oneOf(Gen.choose(0, 10), Gen.oneOf("John Belushi", "Dan Aykroyd"))) {
    x =>
      x match {
        case s: String => s == "John Belushi" || s == "Dan Aykroyd"
        case x: Int => x >= 0 && x <= 10
      }
  }

  property("Random map should have always size equal to the number of key values (distinct)") =
    forAll(Gen.resultOf((k1: String, v1: Int, k2: String, v2: Int) => Map(k1 -> v1, k2 -> v2))) {
//      m => println(m);
      m => m.size <= 2

    }


  //custom generators:
  val mapGenerator = for {
    x <- Gen.alphaStr
    y <- Gen.choose(1, 200)
  } yield Map(x -> y)

  property("Map should always have key alphastr and value between 1 and 200") =
    forAll(mapGenerator) {
      m => {
        m.toVector(0) match {
          case (s: String, i: Int) => i <= 200 && i >= 1
        }
      }

    }

  val xmlGenerator = for {
    email <- Gen.alphaStr
    domainName <- Gen.alphaStr
    tld <- Gen.listOfN(3, Gen.alphaLowerChar).map(_.foldLeft("")((s: String, c:Char) => s + c))
    context <- Gen.alphaStr

  } yield <email aid="1">
      <full>{email}@{domainName}.{tld}</full>
      <context>
        <type>{context}</type>
      </context>
      <quality>
        <dataHash>52acb5da83677f88f1edd80b79653082</dataHash>
      </quality>
    </email>

  property("xml should have datahash") =
    forAll(xmlGenerator) {
      xmlGenerated => println(xmlGenerated); true
    }



  case class Vector(x: Double, y: Double, z: Double) {
    val squaredModule = x * x + y * y + z * z

    def +(another: Vector): Vector = Vector(x + another.x, y + another.y, z + another.z)
  }


  val VectorGenerator3d = for {
    x <- Gen.choose(-100.0, 100.0)
    y <- Gen.choose(-100.0, 100.0)
    z <- Gen.choose(-100.0, 100.0)
  } yield Vector(x, y, z)

  val pairsOf3dVectors = for {
    v1 <- VectorGenerator3d
    v2 <- VectorGenerator3d
  } yield (v1, v2)

  property("triangular inequality") =
    forAll(pairsOf3dVectors) {
      case (v1, v2) => {
//        println((v1 + v2).squaredModule + ":" + v1.squaredModule + ","  + v2.squaredModule);
        Math.sqrt((v1 + v2).squaredModule) <= Math.sqrt(v1.squaredModule) + Math.sqrt(v2.squaredModule)
      }
    }


}
