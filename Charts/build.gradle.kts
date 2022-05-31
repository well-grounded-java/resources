plugins {
  application
  java
  id("com.github.johnrengelman.shadow") version "7.1.2"
}

application {
  mainClass.set("charts.Main")
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
  implementation("org.jfree:jfreechart:1.5.3")
  implementation("org.jfree:org.jfree.svg:5.0.2")
  implementation("org.jfree:jfreesvg:2.0")
}
