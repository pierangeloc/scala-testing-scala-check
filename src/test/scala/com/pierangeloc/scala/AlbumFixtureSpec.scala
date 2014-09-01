package com.pierangeloc.scala

import org.scalatest.{ShouldMatchers, FunSpec}

/**
 * Created by pierangeloc on 27-8-14.
 */
class AlbumFixtureSpec extends FunSpec with ShouldMatchers {

  def fixture = new {
    val moneyForNothing = new Album("Money For Nothin", 1988, new Artist("Dire Straits"))
  }

  describe("Money for nothing was published in 1988") {
    it("should get year 1988 from the album") {
      fixture.moneyForNothing.year should be(1988)

    }
  }

}
