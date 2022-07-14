package liamgiiv.weregoingtomars
package expedition

import expedition.ExpeditionType.ExpeditionType

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfter, OneInstancePerTest}

class ExpeditionTest
  extends AnyFunSuite
    with OneInstancePerTest
    with BeforeAndAfter {
  info("ExpeditionTest tests follow:")

  val series: Int = 1
  val expeditionType: ExpeditionType = ExpeditionType.A
  val trip: Int = 1
  val aCargo: Cargo = new Cargo(1, MineralType.Chromium, 1)
  val bCargo: Cargo = new Cargo(2, MineralType.Gold, 2)
  val cCargo: Cargo = new Cargo(3, MineralType.Titanium, 3)
  val cargo: Array[Cargo] = Array(aCargo, bCargo)
  val expeditionTestFixture: Expedition = new Expedition(series, expeditionType, trip, cargo)

  test ("addCargo should add Cargo to the expedition by returning a new Expedition."){
    val expectedCargoLength: Int = 3
    val cargoToAdd: Array[Cargo] = Array(cCargo)
    val updatedExpedition: Expedition = expeditionTestFixture.addCargo(cargoToAdd)
    assert(updatedExpedition.cargo.length == expectedCargoLength)
  }
}