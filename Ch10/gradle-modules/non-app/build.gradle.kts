plugins {
  java
  application
}

application {
  mainClass.set("com.wellgrounded.nonapp.Main")
  mainModule.set("com.wellgrounded.nonapp")
}

//java {
    //modularity.inferModulePath.set(true)
//}

tasks.jar {
  manifest {
    attributes("Main-Class" to application.mainClassName)
  }
}

repositories {
  mavenLocal()
  mavenCentral()
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf("src/com.wellgrounded.nonapp/java"))
        }
    }
}

dependencies {
  implementation("com.wellgrounded:modlib:1.1")
}
