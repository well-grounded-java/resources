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
  implementation("com.sun.activation:jakarta.activation:1.2.2")
  implementation("org.glassfish.corba:glassfish-corba-omgapi:4.2.1")
  implementation("javax.transaction:javax.transaction-api:1.3")
  implementation("jakarta.xml.bind:jakarta.xml.bind-api:2.3.3")
  implementation("jakarta.xml.ws:jakarta.xml.ws-api:2.3.3")
  implementation("jakarta.annotation:jakarta.annotation-api:1.3.5")
}
