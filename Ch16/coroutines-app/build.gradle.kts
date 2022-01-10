group = "com.wellgrounded"
version = "0.1.0"

plugins {
  kotlin("jvm") version "1.6.10"
  application
}

application {
  mainClass.set("com.wellgrounded.kotlin.MainKt")
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
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
}
