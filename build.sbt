
lazy val core = project
  .in(file("jarjar-core"))
  .settings(
    crossPaths := false,
    autoScalaLibrary := false,
    organization := "io.get-coursier.jarjar",
    name := "jarjar-core",
    version := "1.0.1-coursier-1",
    libraryDependencies ++= Seq(
      "org.ow2.asm" % "asm-commons" % "5.0.3",
      "org.ow2.asm" % "asm-util" % "5.0.3",
      "com.google.code.findbugs" % "jsr305" % "2.0.2",
      "org.slf4j" % "slf4j-api" % "1.7.12"
    ),
    licenses := Seq("Apache 2.0" -> url("http://opensource.org/licenses/Apache-2.0")),
    homepage := Some(url("https://github.com/coursier/jarjar")),
    scmInfo := Some(ScmInfo(
      url("https://github.com/coursier/jarjar.git"),
      "scm:git:github.com/coursier/jarjar.git",
      Some("scm:git:git@github.com:coursier/jarjar.git")
    )),
    pomExtra := {
      <developers>
        <developer>
          <id>alexarchambault</id>
          <name>Alexandre Archambault</name>
          <url>https://github.com/alexarchambault</url>
        </developer>
      </developers>
    },
    publishMavenStyle := true,
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value)
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases"  at nexus + "service/local/staging/deploy/maven2")
    },
    credentials ++= {
      Seq("SONATYPE_USER", "SONATYPE_PASS").map(sys.env.get) match {
        case Seq(Some(user), Some(pass)) =>
          Seq(Credentials("Sonatype Nexus Repository Manager", "oss.sonatype.org", user, pass))
        case _ =>
          Seq()
      }
    }
  )
