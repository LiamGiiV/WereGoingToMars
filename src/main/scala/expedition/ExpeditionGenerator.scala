package liamgiiv.weregoingtomars
package expedition

import files.{FileManager, L_File}

import scala.collection.mutable.ArrayBuffer

class ExpeditionGenerator(
    dataSourceFilePath: String,
    destinationFilePath: String
) {
  private val filePath: String = dataSourceFilePath
  private val expectedHeader: String =
    "ExpeditionNumber,ExpeditionType,TripNumber,Quantity,Mineral,Price"
  private val expectedLineIndex: Int = 0
  private val expectedCharIndex: Int = 1

  def go(): Boolean = {
    val headerlessData = getHeaderlessData()
    if (headerlessData.isEmpty) {
      return false
    }

    val parsedExpeditions: Array[Expedition] = parseHeaderlessDataToExpedition(
      headerlessData.get
    )

    val collatedCargoData: Map[String, Int] =
      CargoCollator.collateExpeditionCargo(parsedExpeditions)

    val fileManager: FileManager = new FileManager("")
    if (
      !fileManager.writeFile(
        destinationFilePath,
        ExpeditionReport.generateReport(collatedCargoData)
      )
    ) {
      println("Report writing failed :(")
      return false
    }
    true
  }

  def parseHeaderlessDataToExpedition(
      dataIn: Array[String]
  ): Array[Expedition] = {

    val dataLines: ArrayBuffer[Array[String]] =
      new ArrayBuffer[Array[String]]()
    for (line <- dataIn) {
      val temp: Array[String] = line.split(",", -1)
      dataLines.addOne(temp)
    }

    val expeditionBuffer: ArrayBuffer[Expedition] =
      new ArrayBuffer[Expedition]()
    for (line <- dataLines) {
      val tempSeries: Int = line(0).toInt
      var tempExpeditionType: ExpeditionType.Value = ExpeditionType.A

      line(1) match {
        case "A" => tempExpeditionType = ExpeditionType.A
        case "B" => tempExpeditionType = ExpeditionType.B
        case _   => println("Unexpected ExpeditionType case!")
      }

      val tempTrip: Int = line(2).toInt
      val tempCargoQuantity: Int = line(3).toInt
      var tempCargoType: MineralType.Value = MineralType.Chromium

      line(4) match {
        case "Chromium" => tempCargoType = MineralType.Chromium
        case "Gold"     => tempCargoType = MineralType.Gold
        case "Titanium" => tempCargoType = MineralType.Titanium
        case _          => println("Unknown tempCargoType case!")
      }

      val tempCargoPrice: Float = line(5).toFloat
      val tempCargo: Cargo =
        new Cargo(tempCargoQuantity, tempCargoType, tempCargoPrice)

      val tempExpedition: Expedition = new Expedition(
        tempSeries,
        tempExpeditionType,
        tempTrip,
        tempCargo
      )
      expeditionBuffer.addOne(tempExpedition)
    }
    expeditionBuffer.toArray
  }

  def getHeaderlessData(): Option[Array[String]] = {
    val fileManager: FileManager = new FileManager(filePath)
    val fileData = fileManager.readFile
    if (fileData.isEmpty) {
      println("fileData is empty :(")
      return None
    }
    if (!validateHeaderFromL_File(fileData.get)) {
      println("header is invalid :(")
      return None
    }
    var first: Boolean = true
    val headerlessExpeditionData: ArrayBuffer[String] =
      new ArrayBuffer[String]()

    // TODO Replace with a more stylistically functional solution
    for (line <- fileData.get.lines) {
      if (!first) {
        headerlessExpeditionData.addOne(line)
      }
      first = false
    }
    Some(headerlessExpeditionData.toArray)
  }

  def validateHeaderFromL_File(L_FileIn: L_File): Boolean = {
    if (
      L_FileIn
        .lines(expectedLineIndex)
        .substring(expectedCharIndex) == expectedHeader
    ) true
    else false
  }
}
