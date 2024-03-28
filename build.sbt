import sbt.Keys._
import Helpers._
import org.scalajs.linker.interface.ModuleSplitStyle

ThisBuild / scalaVersion := monsterScalaVersion

lazy val tsClient = (projectMatrix in file("monster-app/packages/monster-scala-client"))
  .settings(name := "crate-monster-ts-client")
  .frontendProject

lazy val blueprint = (projectMatrix in file("crate/blueprint"))
  .settings(name := "crate-blueprint")
  .crossPlatformProject

lazy val aws = (projectMatrix in file("crate/provider-aws"))
  .settings(name := "crate-monster-aws")
  .crossPlatformProject

lazy val gcp = (projectMatrix in file("crate/provider-gcp"))
  .settings(name := "crate-monster-gcp")
  .crossPlatformProject
  
lazy val azure = (projectMatrix in file("crate/provider-azure"))
  .settings(name := "crate-monster-azure")
  .crossPlatformProject

lazy val kubernetes = (projectMatrix in file("crate/provider-kubernetes"))
  .settings(name := "crate-monster-kubernetes")
  .crossPlatformProject

lazy val monsterScalaAPI = (projectMatrix in file("crate/monster-api"))
  .settings(name := "crate-monster-api")
  .crossPlatformProject

lazy val monsterCloudflareAPI = (projectMatrix in file("monster-app/packages/monster-scala-cloudflare-api"))
  .settings(name := "crate-monster-api")
  .crossPlatformProject
