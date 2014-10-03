name := "countries-webapp"

version := "1.0"

scalaVersion := "2.10.2"

unmanagedResourceDirectories in Compile <++= baseDirectory { base =>
    Seq( base / "src/main/webapp" )
}

Revolver.settings

resolvers ++= Seq(
  "sonatype releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "typesafe repo" at "http://repo.typesafe.com/typesafe/releases/",
  "spray repo" at "http://repo.spray.io/"
)

val akkaV = "2.3.5"

val sprayV = "1.3.1"

libraryDependencies ++= Seq(
 "ch.qos.logback" % "logback-classic" % "1.1.2"
 ,"com.typesafe.akka" % "akka-actor_2.10" % "2.3.5"
 ,"com.typesafe.akka" % "akka-slf4j_2.10" % "2.3.5"
 ,"com.h2database" % "h2" % "1.3.166"
 ,"net.fwbrasil" % "activate-jdbc_2.10" % "1.6.2"
 ,"net.fwbrasil" % "activate-spray-json_2.10" % "1.6.2"
 ,"io.spray" % "spray-can" % sprayV
 ,"io.spray" % "spray-routing" % sprayV
 ,"io.spray" %% "spray-json" % "1.2.6"
 ,"io.spray" % "spray-testkit" % sprayV  % "test"
)