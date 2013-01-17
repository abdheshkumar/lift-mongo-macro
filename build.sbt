    organization := "org.eiennohito"

name := "test"

scalaVersion := "2.10.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

resolvers += Opts.resolver.sonatypeSnapshots

libraryDependencies ++= {
  val liftVersion = "2.5-SNAPSHOT" // Put the current/latest lift version here
  Seq(    
    "net.liftweb" %% "lift-mongodb-record" % liftVersion,    
	"javax.servlet" % "servlet-api" % "2.5" % "provided")
}

libraryDependencies ++=  {
  val rogueVer = "2.0.0-RC1-SNAPSHOT"
  Seq(
    "com.foursquare" %% "rogue-lift" % rogueVer intransitive(),
    "com.foursquare" %% "rogue-core" % rogueVer intransitive(),
    "com.foursquare" %% "rogue-field" % rogueVer intransitive()
  )
}

libraryDependencies ++=  Seq(
        "org.scala-lang" % "scala-reflect" % "2.10.0",
        "org.scala-lang" % "scala-compiler" % "2.10.0"
)

scalacOptions ++= Seq("-Xlog-implicits", "-Xlog-implicit-conversions", "-Ymacro-debug-lite", "-Xlog-free-terms")

