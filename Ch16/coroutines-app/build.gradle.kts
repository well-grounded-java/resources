group = "com.wellgrounded"
version = "0.1.0"

plugins {
  kotlin("jvm") version "1.4.10"
  application
}

application {
  mainClassName = "com.wellgrounded.kotlin.MainKt"
}

tasks.jar {
  manifest {
    attributes("Main-Class" to application.mainClassName)
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
