package com.pierangeloc.scala

import org.scalatest.{ShouldMatchers, FunSpec, OneInstancePerTest}

/**
 * Created by pierangeloc on 27-8-14.
 */
class AlbumListOneInstancePerTestSpec extends FunSpec with ShouldMatchers with OneInstancePerTest {

  //with OneInstancePerTest, a new suite is actually created per test
  val fixture = new {

    import scala.collection.mutable._

    val cream = new Artist("Cream")
    val creamAlbums = new ListBuffer[Album]()
    creamAlbums += new Album("Sunshine Of your Love", 1977, cream)
  }

  describe("Given A discography with only 1 album") {

    it("After adding 1 album it should contain 2 albums") {
      val creamAlbums = fixture.creamAlbums
      creamAlbums += new Album("Crossroads", 1977, fixture.cream)
      creamAlbums.size should be(2)
    }

    it("After adding 2 album it should contain 3 albums, and not be affected by previous test!!!") {
      val creamAlbums = fixture.creamAlbums
      creamAlbums += new Album("Crossroads", 1977, fixture.cream)
      creamAlbums += new Album("Crossroads2", 1977, fixture.cream)

      creamAlbums.size should be(3)
    }

  }

}