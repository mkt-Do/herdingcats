scalaVersion := "2.13.1"

scalacOptions += "-Ymacro-annotations"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.0.0",
  "org.typelevel" %% "simulacrum" % "1.0.0",
  "org.scalactic" %% "scalactic" % "3.2.0",
  "org.scalatest" %% "scalatest" % "3.2.0" % "test"
)
