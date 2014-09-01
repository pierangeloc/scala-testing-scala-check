package com.pierangeloc.scala

import org.scalatest.{ShouldMatchers, FunSpec}

/**
 * Created by pierangeloc on 24-8-14.
 */
class AlbumTest extends FunSpec with ShouldMatchers {

  describe("An Album") {
    it("can add an Artist object to the album") {
      val album = new Album("Thriller", 1981, new Artist("Michael Jackson"))
      album.artist.name should be ("Michael Jackson")
    }
  }

  describe("An Album") {
    it("can have  a year attached to it") {
      val album = new Album("Making Movies", 1982, new Artist("Dire Straits"))
      album.year should(be(1982))
    }
  }

}
