package liamgiiv.weregoingtomars

import expedition.ExpeditionGenerator
import greatUiUxFeatures.Welcomer

object Main {
  def main(args: Array[String]): Unit = {
    // Introduce the program
    Welcomer.theWelcome()

    // Load the file
    val expeditionDataGenerator =
      new ExpeditionGenerator(
        "./data/expeditions.csv",
        "./data/minerals.csv"
      )
    if (expeditionDataGenerator.go()) {
      println("Success (but never trust hardcoded messages)!")
      return
    }
  }
}
