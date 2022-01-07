plugins {
  id("org.beryx.jlink") version("2.23.3")
}

repositories {
  mavenCentral()
}

application {
  mainModule.set("wgjd.discovery")
  mainClass.set("wgjd.discovery.Discovery")
}

java {
    modularity.inferModulePath.set(true)
}

sourceSets {
  main {
    java {
      setSrcDirs(listOf("src/wgjd.discovery"))
    }
  }
}

tasks.withType<JavaCompile> {
  options.compilerArgs = listOf("--add-exports", "jdk.internal.jvmstat/sun.jvmstat.monitor=wgjd.discovery")
}

tasks.jar {
  manifest {
    attributes("Main-Class" to application.mainClass)
  }
}

jlink {
  targetPlatform("local", System.getProperty("java.home"))
  //targetPlatform("linux-x64", "/Users/jasonclark/Downloads/linux_jdk-11.0.10+9")

  launcher{
    jvmArgs = listOf("--add-exports", "jdk.internal.jvmstat/sun.jvmstat.monitor=wgjd.discovery")
  }
}
