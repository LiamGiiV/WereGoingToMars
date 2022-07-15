package liamgiiv.weregoingtomars
package expedition

import expedition.ExpeditionType.ExpeditionType

class Expedition(
    seriesIn: Int,
    typeIn: ExpeditionType,
    tripIn: Int,
    cargoIn: Array[Cargo] // TODO factor into `: Cargo`
) {
  val series: Int = seriesIn
  val expeditionType: ExpeditionType = typeIn
  val trip: Int = tripIn
  val cargo: Array[Cargo] = cargoIn

  // Method is useless in this design
  def addCargo(cargoIn: Array[Cargo]): Expedition = {
    val newCargo: Array[Cargo] = Array.concat(this.cargo, cargoIn)
    val returnExpedition: Expedition =
      new Expedition(this.series, this.expeditionType, this.trip, newCargo)
    returnExpedition
  }
//  // Should not be on the Expedition object
//  def collateExpeditionCargo(
//      expeditionDataIn: Array[Expedition]
//  ): Map[String, Int] = {
//    val material: Map[String, Int] = Map()
//    for (expedition <- expeditionDataIn) {
//      material + (expedition.cargo(0).mineralType.toString, expedition
//        .cargo(0)
//        .quantity)
//    }
//    material
//  }
}

object ExpeditionType extends Enumeration {
  type ExpeditionType = Value
  val A, B = Value
}

object CargoCollator {
  def collateExpeditionCargo(
      expeditionDataIn: Array[Expedition]
  ): Map[String, Int] = {
// %
//    println("Printing parsed expeditions inside of Cargo Collator")
//    for (line <- expeditionDataIn) {
//      println(
//        line.cargo(0).mineralType.toString + " - Quantity: " + line
//          .cargo(0)
//          .quantity
//      )
//    }

    var material: Map[String, Int] = Map[String, Int]()
    var chromiumAccumulator: Int = 0
    var goldAccumulator: Int = 0
    var titaniumAccumulator: Int = 0
    for (expedition <- expeditionDataIn) {
      expedition.cargo(0).mineralType.toString match {
        case "Chromium" => chromiumAccumulator += expedition.cargo(0).quantity
        case "Gold"     => goldAccumulator += expedition.cargo(0).quantity
        case "Titanium" => titaniumAccumulator += expedition.cargo(0).quantity
        case _          => println("Unknown mineralTypeCase case!")
      }
    }
//    {
//      val tempMaterialElement: (String, Int) = (
//        expedition.cargo(0).mineralType.toString,
//        expedition
//          .cargo(0)
//          .quantity
//      )
//      material += tempMaterialElement
//    }

    val chromiumEntry: (String, Int) =
      (MineralType.Chromium.toString, chromiumAccumulator)
    val goldEntry: (String, Int) =
      (MineralType.Gold.toString, goldAccumulator)
    val titaniumEntry: (String, Int) =
      (MineralType.Titanium.toString, titaniumAccumulator)

    material += chromiumEntry
    material += goldEntry
    material += titaniumEntry
// %
//    println("Printing map of collated materials")
//    for (line <- material) {
//      println(line._1 + " - Quantity: " + line._2)
//    }

    material
  }
}
