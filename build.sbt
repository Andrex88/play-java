
name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "com.datastax.cassandra"%"cassandra-driver-core"%"2.2.0-rc3",
  "com.datastax.cassandra"%"cassandra-driver-mapping"%"2.2.0-rc3",
  "com.google.code.gson"%"gson"%"2.3.1"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator


fork in run := true