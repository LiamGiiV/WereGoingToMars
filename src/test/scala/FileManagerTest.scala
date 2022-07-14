package liamgiiv.weregoingtomars

import files.FileManager

import org.scalatest.{BeforeAndAfter, OneInstancePerTest}
import org.scalatest.funsuite.AnyFunSuite

import java.nio.file.Paths
import scala.language.postfixOps
import scala.reflect.io.Path

class FileManagerTest
    extends AnyFunSuite
    with OneInstancePerTest
    with BeforeAndAfter {
  info("FileOpenerTest tests follow:")

  val validPathToCsvFile: String = "./src/test/fixtures/valid.csv"
  val invalidPathToCsvFile: String = "./pokemon-global-offensive.csv"
  val validPathToNonCsvFile: String = "./src/test/fixtures/notacsv.png"
  val validPathToWriteFile: String = "./src/test/fixtures/testWrite.csv"
  val validPathToDeleteFile: String = validPathToWriteFile

  val numberOfValidLines: Int = 2
  val fileLinesFixture: Array[String] =
    Array("this, is, one, line \n", "this, is, another, line")

  val emptyLinesFixture: Array[String] = Array()

  test("CSV file should open successfully.") {
    val fileManagerFixture = new FileManager(validPathToCsvFile)
    assert(fileManagerFixture.managedFile.isDefined)
  }

  test("Nonexistent file should be treated as invalid via Option - none.") {
    val fileManagerFixture = new FileManager(invalidPathToCsvFile)
    assert(fileManagerFixture.managedFile.isEmpty)
  }

  test(
    "Invalid file extension should be treated as invalid via Option - none."
  ) {
    val fileManagerFixture = new FileManager(validPathToNonCsvFile)
    assert(fileManagerFixture.managedFile.isEmpty)
  }

  test("File should exist at expected filepath after writing.") {
    val newFileManagerFixture = new FileManager(validPathToWriteFile)

    // read the file and fail
    val failedRead = newFileManagerFixture.readFile
    assert(failedRead.isEmpty)

    // write the new file
    assert(
      newFileManagerFixture.writeFile(
        validPathToWriteFile,
        fileLinesFixture
      )
    )

    // read the new file
    val successfulRead = newFileManagerFixture.readFile
    assert(successfulRead.isDefined)

    // clean up test fixtures
    assert(newFileManagerFixture.deleteFile(validPathToDeleteFile))
  }

  test(
    "Files should be deleted by deleteFile."
  ) {
    val fileManagerFixture = new FileManager(validPathToWriteFile)
    assert(
      fileManagerFixture.writeFile(
        validPathToWriteFile,
        fileLinesFixture
      )
    )

    val readToTest = fileManagerFixture.readFile
    assert(readToTest.isDefined)
    assert(fileManagerFixture.deleteFile(readToTest.get.filePath.toString()))
  }

//  test("File should contain the expected number of lines after writing.") {
//  }

//  test(
//    "Attempting to write an empty file should write nothing and return false."
//  ) {
//  }
}
