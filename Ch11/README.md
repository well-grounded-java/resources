# Chapter 11 - Building with Maven & Gradle

## `Ch11`

This directory contains multiple examples using Gradle and Maven.

It's best to load individual projects as the Java requirements between some of
the examples are different.

All builds can be run at the command-line via the typical `mvn verify` or
`./gradlew check` commands.

### Maven

* `maven-example` - Java 11+ recommended, basic Maven usage
* `maven-ee-8` - Java 8 required, example of Java EE deprecations
* `maven-ee-11` - Java 11+ required, example of updated references for Java EE deprecations
* `maven-modules/mod-lib` - Java 11+, a modular library for use with app examples. `mvn install` this project before building the others.
* `maven-modules/mod-app` - Java 11+, a modular application using `mod-lib`
* `maven-modules/non-app` - Java 11+, a non-modular application using `mod-lib`
* `maven-multi-release` - Java 11+, a library JAR using multi-release versioning. Use `mvn package` to build JAR.
* `wellgrounded-maven-plugin` - A Maven plugin implementation

### Gradle

* `gradle-example` - Java 11+ recmomended, basic Gradle usage
* `gradle-ee-8` - Java 8 required, example of Java EE deprecations
* `gradle-ee-11` - Java 11+ required, example of updated references for Java EE deprecations
* `gradle-modules/mod-lib` - Java 11+, a modular library for use with app examples. Use `./gradlew assemble` to create JAR for use from other projects.
* `gradle-modules/mod-app` - Java 11+, a modular application using `mod-lib`
* `gradle-modules/non-app` - Java 11+, a non-modular application using `mod-lib`
* `gradle-jlink` - Java 11+ required, example of using `jlink` to create single executable. Run `./gradlew jlink` to generaate output under `build/image`.
