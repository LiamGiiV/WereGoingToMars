package liamgiiv.weregoingtomars

import files.File

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfter, OneInstancePerTest}

class FileTest extends AnyFunSuite with OneInstancePerTest with BeforeAndAfter {
  info("FileTest tests follow:")

  val filepathWithoutFileExtension: String = "./my/bustedpath"

  test("Files without extensions should have an empty fileExtension value") {
    val fileWithoutFileExtension: File = new File(filepathWithoutFileExtension)
    assert(fileWithoutFileExtension.fileExtension.isEmpty)
  }
}
