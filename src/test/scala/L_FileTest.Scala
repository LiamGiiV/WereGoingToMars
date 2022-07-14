package liamgiiv.weregoingtomars

import files.{FileManager, L_File}

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.{BeforeAndAfter, OneInstancePerTest}

class L_FileTest
    extends AnyFunSuite
    with OneInstancePerTest
    with BeforeAndAfter {
  info("FileTest tests follow:")

  val filepathWithoutFileExtension: String = "./my/bustedpath"
  val validFile: (String, Int, String, String) =
    (
      "./src/test/fixtures/valid.csv",
      2,
      "csv",
      ".\\src\\test\\fixtures\\valid.csv"
    )
  val fileManagerFixture = new FileManager(validFile._1)

  test("Files without extensions should not be represented by Option - none.") {
    val fileManagerWithBadPath = new FileManager(filepathWithoutFileExtension)
    assert(fileManagerWithBadPath.managedFile.isEmpty)
  }

  test("Files should have the expected number of lines.") {
    assert(fileManagerFixture.managedFile.get.lines.length == validFile._2)
  }

  test("Files should have the expected file extensions.") {
    assert(fileManagerFixture.managedFile.get.fileExtension == validFile._3)
  }

  // TODO verify that this behaviour is consistent across OSes
  test("Files should have the expected filepath.") {
    assert(
      fileManagerFixture.managedFile.get.filePath.toString() == validFile._4
    )
  }
}
