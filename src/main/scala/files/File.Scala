package liamgiiv.weregoingtomars
package files

import scala.collection.mutable.ArrayBuffer
import scala.reflect.io.Path

class File(filePathIn: String) {
  val filePath: Path = Path.string2path(filePathIn)
  val fileExtension: Option[String] = testFileExtension(filePath)
  val lines: ArrayBuffer[String] = new ArrayBuffer[String]

  private def testFileExtension(filePathIn: Path): Option[String] = {
    val comparator = filePathIn.toString().split("\\.").last
    val comparatorAdjustedForJavaJank: String =
      filePathIn.toString().slice(1, filePathIn.toString().length)
    if (comparator.equals(comparatorAdjustedForJavaJank)) {
      return None
    }
    Some(comparator)
  }
}
