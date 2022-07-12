package liamgiiv.weregoingtomars

import files.{File, FileOpener}
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
  val validCSVFileFixture: File = new File(validPathToCsvFile)
  val invalidCSVFileFixture: File = new File(invalidPathToCsvFile)
  val nonCSVFileFixture: File = new File(validPathToNonCsvFile)

  test("CSV file should open successfully.") {
    val fileOpenerFixture = new FileOpener(validCSVFileFixture)
    val openAttemptResult: Option[File] = fileOpenerFixture.readFile
    assert(openAttemptResult.isDefined)
  }

  test("Invalid file should be treated as invalid via Option - none.") {
    val fileOpenerFixture = new FileOpener(invalidCSVFileFixture)
    val openAttemptResult: Option[File] = fileOpenerFixture.readFile
    assert(openAttemptResult.isEmpty)
  }

  test(
    "Invalid file extension should be treated as invalid via Option - none."
  ) {
    val fileOpenerFixture = new FileOpener(nonCSVFileFixture)
    val openAttemptResult: Option[File] = fileOpenerFixture.readFile
    assert(openAttemptResult.isEmpty)
  }
}
