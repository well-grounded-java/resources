group = "com.wellgrounded"
version = "0.1.0"

plugins {
  kotlin("jvm") version "1.4.10"
  application
}

application {
  mainClass.set("com.wellgrounded.kotlin.Main")
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
}
