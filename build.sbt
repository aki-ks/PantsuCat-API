organization := "de.kaysubs"
name := "PantsuCat-API"
version := "0.1-SNAPSHOT"

description := "Java API for pantsu.cat"

javacOptions in (Compile, compile) ++= Seq("-source", "1.8", "-target", "1.8")

crossPaths := false // drop off Scala suffix from artifact names.
autoScalaLibrary := false // exclude scala-library from dependencies

val httpClientVersion = "4.5.5"
val gsonVersion = "2.8.2"
lazy val kaysubsCommons = RootProject(uri("https://github.com/kaysubs/kaysub-commons.git"))

dependsOn(kaysubsCommons)

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % httpClientVersion
libraryDependencies += "org.apache.httpcomponents" % "httpmime" % httpClientVersion
libraryDependencies += "com.google.code.gson" % "gson" % gsonVersion
