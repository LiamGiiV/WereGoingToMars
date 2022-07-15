package liamgiiv.weregoingtomars
package expedition

import scala.collection.mutable.ArrayBuffer

object ExpeditionReport {
  def generateReport(
      collatedExpeditionCargo: Map[String, Int]
  ): Array[String] = {
    val reportLines: ArrayBuffer[String] = ArrayBuffer(generateReportHeader())
    for (line <- generateReportLines(collatedExpeditionCargo)) {
      reportLines.addOne(line)
    }
    reportLines.toArray
  }

  private def generateReportHeader(): String = {
    "Mineral, Quantity"
  }

  // TODO factor this to remove the transient coupling, report should manage
  private def generateReportLines(
      collatedExpeditionCargo: Map[String, Int]
  ): Array[String] = {
    val lineBuffer: ArrayBuffer[String] = new ArrayBuffer[String]

    var chromiumAmount: Int = 0
    var goldAmount: Int = 0
    var titaniumAmount: Int = 0
    var i: Int = 1
    for (entry <- collatedExpeditionCargo) {
      println(i)
      i = i + 1
      entry._1 match {
        // TODO replace these magic strings
        case "Chromium" => chromiumAmount += entry._2
        case "Gold"     => goldAmount += entry._2
        case "Titanium" => titaniumAmount += entry._2
        case _          => println("Unknown mineralType case!")
      }
    }

    lineBuffer.addOne(
      MineralType.Chromium.toString + ", " + chromiumAmount.toString
    )
    lineBuffer.addOne(
      MineralType.Gold.toString + ", " + goldAmount.toString
    )
    lineBuffer.addOne(
      MineralType.Titanium.toString + ", " + titaniumAmount.toString
    )
    lineBuffer.toArray
  }
}
