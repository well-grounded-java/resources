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
  // Redis
  implementation("redis.clients:jedis:3.6.0")

  // Postgres
  implementation("org.postgresql:postgresql:42.2.1")

  testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.1")

  testImplementation("org.testcontainers:testcontainers:1.15.3")
  testImplementation("org.testcontainers:junit-jupiter:1.15.3")

  testImplementation("org.testcontainers:postgresql:1.15.3")

  testImplementation("org.testcontainers:selenium:1.15.3")
  testImplementation("org.seleniumhq.selenium:selenium-remote-driver:3.141.59")
  testImplementation("org.seleniumhq.selenium:selenium-chrome-driver:3.141.59")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
