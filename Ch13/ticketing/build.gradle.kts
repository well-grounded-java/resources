plugins {
  application
  java
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
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
  testImplementation("org.mockito:mockito-core:4.1.0")

  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.named<Test>("test") {
  useJUnitPlatform()
}