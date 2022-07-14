package liamgiiv.weregoingtomars

import files.FileManager

object Main {
  def main(args: Array[String]): Unit = {
    val lines: Array[String] =
      Array("These, are, some, test, lines\n", "They, are, Pretty, Cool!\n")

    val validPathToWriteFile: String = "./src/test/fixtures/testWrite.csv"
    val outputFileManager: FileManager = new FileManager(validPathToWriteFile)
    outputFileManager.writeFile(validPathToWriteFile, lines)
  }
}
