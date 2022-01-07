plugins {
  java
  application
}

application {
  mainClass.set("com.wellgrounded.modapp.Main")
  mainModule.set("com.wellgrounded.modapp")
}

tasks.jar {
  manifest {
    attributes("Main-Class" to application.mainClass)
  }
}

repositories {
  mavenLocal()
  mavenCentral()
}

java {
    modularity.inferModulePath.set(true)
}

sourceSets {
  main {
    java {
      setSrcDirs(listOf("src/com.wellgrounded.modapp/java"))
    }
  }
}

dependencies {
  implementation(files("../mod-lib/build/libs/gradle-mod-lib.jar"))
}
