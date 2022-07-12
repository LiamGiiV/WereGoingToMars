package liamgiiv.weregoingtomars

import files.{File, FileManager}
import org.scalatest.{BeforeAndAfter, OneInstancePerTest}
import org.scalatest.funsuite.AnyFunSuite

import scala.language.postfixOps

class FileOpenerTest
    extends AnyFunSuite
    with OneInstancePerTest
    with BeforeAndAfter {
  info("FileOpenerTest tests follow:")

  val validPathToCsvFile: String = "./src/test/fixtures/valid.csv"
  val invalidPathToCsvFile: String = "./pokemon-global-offensive.csv"
  val validPathToNonCsvFile: String = "./src/test/fixtures/notacsv.png"

  test("CSV file should open successfully.") {
    val fileManagerFixture = new FileManager(validPathToCsvFile)
    assert(fileManagerFixture.managedFile.isDefined)
  }

  test("Invalid file should be treated as invalid via Option - none.") {
    val fileManagerFixture = new FileManager(invalidPathToCsvFile)
    assert(fileManagerFixture.managedFile.isEmpty)
  }

  test(
    "Invalid file extension should be treated as invalid via Option - none."
  ) {
    val fileManagerFixture = new FileManager(validPathToNonCsvFile)
    assert(fileManagerFixture.managedFile.isEmpty)
  }
}
