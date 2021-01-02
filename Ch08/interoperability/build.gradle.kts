group = "com.wellgrounded"
version = "0.1.0"

plugins {
  kotlin("jvm") version "1.4.10"
  application
}

application {
  mainClassName = "com.wellgrounded.kotlin.Main"
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
}
