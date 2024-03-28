import sbt.internal._

object Helpers {
    val toolkitVersion = "0.1.3"
    val toolkit = "org.typelevel" %% "toolkit" % toolkitVersion
    val toolkitTest = "org.typelevel" %% "toolkit-test" % toolkitVersion

    lazy val monsterScalaVersion = "3.4.0"
    lazy val monsterScalaVersions = Seq(monsterScalaVersion)

    implicit class ProjectMatrixHelper(proj: ProjectMatrix) {
        def withTypelevelToolkit = proj.settings(
            libraryDependencies += toolkit,
            libraryDependencies += (toolkitTest % Test)
        )
        
        def crossPlatformProject = proj.jvmPlatform(scalaVersions = monsterScalaVersions)
            .jsPlatform(scalaVersions = monsterScalaVersions)
            .nativePlatform(scalaVersions = monsterScalaVersions)
            .withTypelevelToolkit

        def backendProject = proj.jvmPlatform(scalaVersions = monsterScalaVersions)
            .nativePlatform(scalaVersions = monsterScalaVersions)
            .withTypelevelToolkit

        def frontendProject = proj.jsPlatform(scalaVersions = monsterScalaVersions)
            .settings(
                scalacOptions ++= Seq("-encoding", "utf-8", "-deprecation", "-feature"),
                scalaJSUseMainModuleInitializer := true,
                scalaJSLinkerConfig ~= {
                    _.withModuleKind(ModuleKind.ESModule)
                        .withModuleSplitStyle(ModuleSplitStyle.SmallModulesFor(List("crate.monster")))
                },
                libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.4.0",
            )
            .withTypelevelToolkit
    }
}