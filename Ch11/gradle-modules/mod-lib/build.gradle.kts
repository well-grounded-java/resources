plugins {
  `java-library`
}

repositories {
  mavenCentral()
}

sourceSets {
  main {
    java {
      setSrcDirs(listOf("src/com.wellgrounded.modlib/java"))
    }
  }
}
