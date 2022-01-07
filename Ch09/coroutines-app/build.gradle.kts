group = "com.wellgrounded"
version = "0.1.0"

plugins {
  kotlin("jvm") version "1.4.10"
  application
}

application {
  mainClass.set("com.wellgrounded.kotlin.MainKt")
}

task("runCancel", JavaExec::class) {
    mainClass.set("com.wellgrounded.kotlin.CancellingMainKt")
    classpath = sourceSets["main"].runtimeClasspath
}

task("runCoop", JavaExec::class) {
    mainClass.set("com.wellgrounded.kotlin.CoopMainKt")
    classpath = sourceSets["main"].runtimeClasspath
}

tasks.jar {
  manifest {
    attributes("Main-Class" to application.mainClass)
  }
}

repositories {
  mavenCentral()
  mavenLocal()
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
}
