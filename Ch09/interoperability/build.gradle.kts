group = "com.wellgrounded"
version = "0.1.0"

plugins {
  kotlin("jvm") version "1.6.10"
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

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

repositories {
  mavenCentral()
  mavenLocal()
}

dependencies {
  implementation(kotlin("stdlib"))
}
