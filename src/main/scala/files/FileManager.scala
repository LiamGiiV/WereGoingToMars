package liamgiiv.weregoingtomars
package files

import java.io.PrintWriter
import java.nio.file.{Files, Paths}
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

class FileManager(filePathIn: String) {
  val managedFile: Option[L_File] = readFile

  def readFile: Option[L_File] = {
    if (!validateFileTypeAsCSV(filePathIn)) {
      return None
    }
    val lines: ArrayBuffer[String] = new ArrayBuffer[String]
    try {
      for (line <- Source.fromFile(filePathIn).getLines()) {
        lines.addOne(line)
      }
    } catch {
      case e: Exception =>
        return None
    }
    if (lines.nonEmpty) {
      val returnFile: L_File = new L_File(filePathIn)
      for (line <- lines) {
        returnFile.lines.addOne(line)
      }
      Source.fromFile(filePathIn).close()
      return Some(returnFile)
    }
    None
  }

  def validateFileTypeAsCSV(filePathIn: String): Boolean = {
    val testResult = testFileExtension(filePathIn)
    if (testResult.isDefined) {
      testResult.get.toLowerCase() match {
        case "csv" => return true
        case _ =>
          return {
            println("non CSV File Extension case!")
            false
          }
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

  def writeFile(filePathIn: String, linesIn: Array[String]): Boolean = {
    if (filePathIn.isEmpty || filePathIn.length < 0 || linesIn.isEmpty) {
      if (linesIn.isEmpty) {
        println("I'm empty :(")
      }
      println("I failed :(")
      return false
    }

    val printWriter = new PrintWriter(filePathIn)
    for (line <- linesIn) {
      printWriter.write(line + "\n")
    }
    printWriter.close()
    true
  }

  def deleteFile(filePathIn: String): Boolean = {
    System.gc()
    Thread.sleep(2000) // This is incredibly silly
    val pathToDelete = Paths.get(filePathIn)
    try {
      Files.deleteIfExists(pathToDelete)
    } catch {
      case e: Exception =>
        println(e)
        return false
    }
    true
  }
}
