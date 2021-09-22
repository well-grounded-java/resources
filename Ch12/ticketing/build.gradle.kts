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
  testImplementation("junit:junit:4.13.2")
  testImplementation("org.mockito:mockito-core:3.12.4")
}
