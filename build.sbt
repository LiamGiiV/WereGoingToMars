ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "WereGoingToMars",
    idePackagePrefix := Some("liamgiiv.weregoingtomars")
  )

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.12"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.12" % "test"
libraryDependencies += "org.scalatest" %% "scalatest-funsuite" % "3.2.12" % "test"
