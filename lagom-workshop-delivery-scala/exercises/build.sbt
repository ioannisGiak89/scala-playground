organization in ThisBuild := "com.example"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

lagomCassandraCleanOnStart in ThisBuild := true

val lombok = "org.projectlombok" % "lombok" % "1.16.10"

val step = "05"

def common = Seq(
  javacOptions in compile += "-parameters"
)

lazy val `greeting-api` = project("greeting-api")
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslApi,
      lombok
    )
  )

lazy val `greeting-impl` = project("greeting-impl")
  .enablePlugins(LagomJava)
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslPersistenceCassandra,
      lagomJavadslKafkaBroker,
      lagomJavadslTestKit,
      lombok
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`greeting-api`)

lazy val `whosthere-api` = project("whosthere-api")
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslApi,
      lombok
    )
  )

lazy val `whosthere-impl` = project("whosthere-impl")
  .enablePlugins(LagomJava)
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslPersistenceCassandra,
      lagomJavadslKafkaClient,
      lagomJavadslTestKit,
      lombok
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`whosthere-api`, `greeting-api`)


def project(id: String) = Project(s"${id}_${step}", base = file(id))
  .settings(javacOptions in compile ++= Seq("-encoding", "UTF-8", "-source", "1.8", "-target", "1.8", "-Xlint:unchecked", "-Xlint:deprecation"))
  .settings(jacksonParameterNamesJavacSettings: _*)

// See https://github.com/FasterXML/jackson-module-parameter-names
lazy val jacksonParameterNamesJavacSettings = Seq(
  javacOptions in compile += "-parameters"
)

aggregateProjects(`whosthere-impl`, `whosthere-api`, `greeting-impl`, `greeting-api`)




