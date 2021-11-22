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
  // During migration, we can mix and match JUnit 4 + 5
  testImplementation("junit:junit:4.13.2")
  testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.7.1")

  // Once we're all on JUnit 5, only these should be needed
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.1")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
