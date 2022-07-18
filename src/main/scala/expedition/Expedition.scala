package liamgiiv.weregoingtomars
package expedition

import expedition.ExpeditionType.ExpeditionType

class Expedition(
    seriesIn: Int,
    typeIn: ExpeditionType,
    tripIn: Int,
    cargoIn: Cargo
) {
  val series: Int = seriesIn
  val expeditionType: ExpeditionType = typeIn
  val trip: Int = tripIn
  val cargo: Cargo = cargoIn
}

object ExpeditionType extends Enumeration {
  type ExpeditionType = Value
  val A, B = Value
}

object CargoCollator {
  def collateExpeditionCargo(
      expeditionDataIn: Array[Expedition]
  ): Map[String, Int] = {

    var material: Map[String, Int] = Map[String, Int]()
    var chromiumAccumulator: Int = 0
    var goldAccumulator: Int = 0
    var titaniumAccumulator: Int = 0
    for (expedition <- expeditionDataIn) {
      expedition.cargo.mineralType.toString match {
        case "Chromium" => chromiumAccumulator += expedition.cargo.quantity
        case "Gold"     => goldAccumulator += expedition.cargo.quantity
        case "Titanium" => titaniumAccumulator += expedition.cargo.quantity
        case _          => println("Unknown mineralTypeCase case!")
      }
    }

    val chromiumEntry: (String, Int) =
      (MineralType.Chromium.toString, chromiumAccumulator)
    val goldEntry: (String, Int) =
      (MineralType.Gold.toString, goldAccumulator)
    val titaniumEntry: (String, Int) =
      (MineralType.Titanium.toString, titaniumAccumulator)

    material += chromiumEntry
    material += goldEntry
    material += titaniumEntry

    material
  }
}
