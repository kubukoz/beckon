inThisBuild(
  List(
    organization := "com.kubukoz",
    homepage := Some(url("https://github.com/kubukoz/beckon")),
    licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    developers := List(
      Developer(
        "kubukoz",
        "Jakub Kozłowski",
        "kubukoz@gmail.com",
        url("https://kubukoz.com")
      )
    )
  ))

def compilerPlugins(scalaVersion: String) = (if(!scalaVersion.startsWith("2.13.")) List(
  compilerPlugin("org.scalamacros" % "paradise" % "2.1.1").cross(CrossVersion.full)
) else Nil) ++ List(
  compilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3")
)

val commonSettings = Seq(
  scalaVersion := "2.12.8",
  crossScalaVersions := List("2.11.12", "2.12.8", "2.13.0"),
  scalacOptions ++= Options.all(scalaVersion.value),
  fork in Test := true,
  name := "beckon",
  updateOptions := updateOptions.value.withGigahorse(false), //may fix publishing bug
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.8" % Test
  ) ++ compilerPlugins(scalaVersion.value)
)

val core = project.settings(commonSettings).settings(name += "-core")

val beckon =
  project
    .in(file("."))
    .settings(commonSettings)
    .settings(skip in publish := true)
    .dependsOn(core)
    .aggregate(core)
