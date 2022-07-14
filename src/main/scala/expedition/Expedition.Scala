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
    val material: Map[String, Int] = Map()
    for (expedition <- expeditionDataIn) {
// TODO figure out how to get a list of how much of each thing there is
    }
    material
  }
}
