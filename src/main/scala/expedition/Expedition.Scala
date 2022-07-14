package liamgiiv.weregoingtomars
package expedition

import expedition.ExpeditionType.ExpeditionType

class Expedition(
    seriesIn: Int,
    typeIn: ExpeditionType,
    tripIn: Int,
    cargoIn: Array[Cargo]
) {
  val series: Int = seriesIn
  val expeditionType: ExpeditionType = typeIn
  val trip: Int = tripIn
  val cargo: Array[Cargo] = cargoIn

  def addCargo(cargoIn: Array[Cargo]): Expedition = {
    val newCargo: Array[Cargo] = new Array[Cargo]()
  }
}

object ExpeditionType extends Enumeration {
  type ExpeditionType = Value
  val A, B = Value
}
