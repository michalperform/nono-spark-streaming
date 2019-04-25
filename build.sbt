name := "nono-spark-streaming"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.2"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.4.2"
libraryDependencies += "org.apache.bahir" %% "spark-streaming-twitter" % "2.3.2"