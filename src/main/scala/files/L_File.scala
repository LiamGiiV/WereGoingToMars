package liamgiiv.weregoingtomars
package files

import scala.collection.mutable.ArrayBuffer
import scala.reflect.io.Path

// TODO - Improve design so that L_File is managed exclusively by FileManager
class L_File(filePathIn: String) {
  val filePath: Path = Path.string2path(filePathIn)
  val fileExtension: String = setFileExtension(filePath)
  val lines: ArrayBuffer[String] = new ArrayBuffer[String]

  private def setFileExtension(filePathIn: Path): String = {
    filePathIn.toString().split("\\.").last
  }
}
