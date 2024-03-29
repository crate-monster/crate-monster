import sbt._
import sbt.Keys._
import sbt.internal._
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport.*
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport.*
import org.scalajs.linker.interface.ModuleSplitStyle

object MonsterProject {

  lazy val scalaVersion = "3.4.0"
  lazy val scalaVersions: Seq[String] = Seq(scalaVersion)

  object Helpers {

    implicit class ProjectMatrixHelper(proj: ProjectMatrix) {

      def withTypelevelToolkit = proj.settings(
        libraryDependencies ++= Seq(
          "org.typelevel" %%% "cats-core" % "2.10.0",
          "org.typelevel" %%% "cats-effect" % "3.5.4",
          "co.fs2" %%% "fs2-io" % "3.10.2",
          "org.gnieh" %%% "fs2-data-json" % "1.11.0",
          "io.circe" %%% "circe-jawn" % "0.15.0-M1",
          "org.http4s" %%% "http4s-circe" % "1.0.0-M41",
          "org.http4s" %%% "http4s-fs2-data-json" % "0.4.0",
          "com.monovore" %%% "decline-effect" % "2.4.1",
          "org.typelevel" %%% "cats-core" % "2.10.0" % Test,
          "org.typelevel" %%% "cats-effect-testkit" % "3.5.4" % Test,
          "org.scalameta" %%% "munit" % "1.0.0-M11" % Test,
          "org.typelevel" %%% "munit-cats-effect" % "2.0.0-M4" % Test
        ),
        dependencyOverrides += "org.http4s" %%% "http4s-core" % "1.0.0-M41"
      )

      def withLogging = proj.settings(
        libraryDependencies ++= Seq(
          "com.casualmiracles" %% "treelog-cats" % "1.9.1",
          "com.outr" %%% "scribe-cats" % "3.13.2",
          "com.outr" %%% "scribe" % "3.13.2"
        )
      )

      def withHttpServer = proj.settings(
        libraryDependencies ++= Seq(
          "org.http4s" %%% "http4s-ember-server" % "1.0.0-M41",
        )
      )

      def withHttpClient = proj.settings(
        libraryDependencies ++= Seq(
          "org.http4s" %%% "http4s-ember-client" % "1.0.0-M41",
        )
      )

      def jsProject = proj
        .jsPlatform(scalaVersions = scalaVersions)
        .settings(
          scalacOptions ++= Seq("-encoding", "utf-8", "-deprecation", "-feature"),
          scalaJSLinkerConfig ~= {
            _.withModuleKind(ModuleKind.ESModule)
              .withModuleSplitStyle(ModuleSplitStyle.SmallModulesFor(List("crate.monster")))
              .withOptimizer(false)
          },
          fullOptJS / artifactPath := baseDirectory.value / "index.js",
          fastLinkJS / artifactPath := baseDirectory.value / "index.js"
        )

      def jvmProject = proj
        .jvmPlatform(scalaVersions = scalaVersions)
        .settings(
          libraryDependencies += "org.scala-js" %% "scalajs-stubs" % "1.1.0" % "provided"
        )

      def crossPlatformProject = proj.jvmProject
        .jsProject
        .withTypelevelToolkit
        .withHttpClient
        .withLogging

      def apiWorkerProject = proj.jsProject
        .withTypelevelToolkit
        .withHttpServer
        .withHttpClient
        .withLogging

      def apiClientProject = proj.jsProject
        .withTypelevelToolkit
        .withHttpClient
        .withLogging
    }
  }
}
