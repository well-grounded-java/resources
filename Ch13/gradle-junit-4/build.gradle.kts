plugins {
  application
  java
}

application {
  mainClass.set("com.wellgrounded.Main")
}

tasks.jar {
  manifest {
    attributes("Main-Class" to application.mainClass)
  }
}

repositories {
  mavenCentral()
}

dependencies {
  testImplementation("junit:junit:4.13.2")
}
