package liamgiiv.weregoingtomars
package files

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

class FileOpener(fileIn: File) {
  def validateFileTypeAsCSV(fileIn: File): Boolean = {
    fileIn.fileExtension.toLowerCase() match {
      case "csv" => true
      case _     => false
    }
  }

  def readFile: Option[File] = {
    if (!validateFileTypeAsCSV(fileIn)) {
      return None
    }
    val lines: ArrayBuffer[String] = new ArrayBuffer[String]
    try {
      for (line <- Source.fromFile(fileIn.filePath.toString()).getLines()) {
        lines.addOne(line)
      }
    } catch {
      case e: Exception =>
        println(e)
        return None
    }
    if (lines.nonEmpty) {
      val returnFile: File = new File(fileIn.filePath.toString())
      for (line <- lines) {
        returnFile.lines.addOne(line)
      }
      return Some(returnFile)
    }
    None
  }
}
