plugins {
  application
  java
  id("org.jetbrains.kotlin.jvm") version "1.4.31"
  id("com.github.spotbugs") version "4.3.0"
}

application {
  mainClassName = "com.wellgrounded.Main"
}

tasks.jar {
  manifest {
    attributes("Main-Class" to application.mainClassName)
  }
}

repositories {
  mavenCentral()
}

dependencies {
  testImplementation("junit:junit:4.13.2")
  testImplementation("org.hamcrest:hamcrest-core:2.2")
}

tasks.withType<com.github.spotbugs.snom.SpotBugsTask>().configureEach {
  reports.create("html") {
    isEnabled = true
    setStylesheet("fancy-hist.xsl")
  }
}

open class YoPluginExtension {
    var count: Int = 1
}

class YoPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create<YoPluginExtension>("yo")
        project.task("yo") {
            doLast {
              repeat(extension.count) {
                println("yo")
              }
            }
        }
    }
}

apply<YoPlugin>()

configure<YoPluginExtension> {
    count = 4
}
