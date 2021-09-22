plugins {
  application
  java
  kotlin("jvm") version "1.5.21"
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
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")

  testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.15")
  testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:2.0.15")
}

tasks.named<Test>("test") {
  useJUnitPlatform() {
    includeEngines("spek2")
  }
}
