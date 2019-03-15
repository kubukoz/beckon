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

val compilerPlugins = List(
  compilerPlugin("org.scalamacros" % "paradise" % "2.1.1").cross(CrossVersion.full),
  compilerPlugin("org.spire-math" %% "kind-projector" % "0.9.9")
)

val commonSettings = Seq(
  scalaVersion := "2.12.8",
  scalacOptions ++= Options.all,
  fork in Test := true,
  name := "beckon",
  updateOptions := updateOptions.value.withGigahorse(false), //may fix publishing bug
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % Test
  ) ++ compilerPlugins
)

val core = project.settings(commonSettings).settings(name += "-core")

val beckon =
  project
    .in(file("."))
    .settings(commonSettings)
    .settings(skip in publish := true)
    .dependsOn(core)
    .aggregate(core)
