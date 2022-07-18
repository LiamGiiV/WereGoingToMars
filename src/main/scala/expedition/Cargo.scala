package liamgiiv.weregoingtomars
package expedition

import expedition.MineralType.MineralType

class Cargo(quantityIn: Int, mineralTypeIn: MineralType, priceIn: Float) {
  val quantity: Int = quantityIn
  val mineralType: MineralType = mineralTypeIn
  val price: Float = priceIn
}

object MineralType extends Enumeration {
  type MineralType = Value
  val Chromium, Gold, Titanium = Value
}
