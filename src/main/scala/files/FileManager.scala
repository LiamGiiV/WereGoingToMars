package liamgiiv.weregoingtomars
package files

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

class FileManager(filePathIn: String) {
  val managedFile: Option[File] = readFile

  def readFile: Option[File] = {
    if (!validateFileTypeAsCSV(filePathIn)) {
      return None
    }
    val lines: ArrayBuffer[String] = new ArrayBuffer[String]
    try {
      for (line <- Source.fromFile(filePathIn.toString()).getLines()) {
        lines.addOne(line)
      }
    } catch {
      case e: Exception =>
        return None
    }
    if (lines.nonEmpty) {
      val returnFile: File = new File(filePathIn)
      for (line <- lines) {
        returnFile.lines.addOne(line)
      }
      return Some(returnFile)
    }
    None
  }

  def validateFileTypeAsCSV(filePathIn: String): Boolean = {
    val testResult = testFileExtension(filePathIn)
    if (testResult.isDefined) {
      testResult.get.toLowerCase() match {
        case "csv" => return true
        case _     => return false
      }
    }
    false
  }

  private def testFileExtension(filePathIn: String): Option[String] = {
    val comparator = filePathIn.split("\\.").last
    val comparatorAdjustedForJavaJank: String =
      filePathIn.slice(1, filePathIn.length)
    if (comparator.equals(comparatorAdjustedForJavaJank)) {
      return None
    }
    Some(comparator)
  }
}
