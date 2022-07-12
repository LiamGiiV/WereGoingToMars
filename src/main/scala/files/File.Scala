package liamgiiv.weregoingtomars
package files

import scala.collection.mutable.ArrayBuffer
import scala.reflect.io.Path

class File(filePathIn: String) {
  val filePath: Path = Path.string2path(filePathIn)
  val fileExtension: String = filePathIn.split("\\.").last
  val lines: ArrayBuffer[String] = new ArrayBuffer[String]
}
