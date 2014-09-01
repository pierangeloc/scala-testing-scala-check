name := "Testing Scala"

version := "1.0"

scalaVersion := "2.11.1"

resolvers ++= Seq("snapshot" at "http://scala-tools.org/repo-snapshots",
                  "releases" at "http://scala-tools.org/repo-releases")

libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "2.2.1" % "test",
                            "org.scalacheck" %% "scalacheck" % "1.11.5" % "test")
