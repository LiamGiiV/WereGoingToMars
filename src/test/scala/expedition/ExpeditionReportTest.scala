package liamgiiv.weregoingtomars
package expedition

import expedition.ExpeditionType.ExpeditionType

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfter, OneInstancePerTest}

class ExpeditionReportTest
    extends AnyFunSuite
    with OneInstancePerTest
    with BeforeAndAfter {
  info("ExpeditionReportTest tests follow:")

  val series: Int = 1
  val expeditionType: ExpeditionType = ExpeditionType.A
  val trip: Int = 1
  val cargo: Array[Cargo] = Array(
    new Cargo(1, MineralType.Chromium, 1),
    new Cargo(2, MineralType.Gold, 2),
    new Cargo(3, MineralType.Titanium, 3)
  )
  val expeditionTestFixture: Expedition =
    new Expedition(series, expeditionType, trip, cargo)
}
