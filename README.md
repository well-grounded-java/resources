# resources
Repository for The Well-Grounded Java Developer, 2nd Edition  project resources

## Table of Contents

* [Ch01](https://github.com/well-grounded-java/resources/tree/main/Ch01) - Introducing modern Java
* [Ch02](https://github.com/well-grounded-java/resources/tree/main/Ch02) - Java modules
* [Ch02](https://github.com/well-grounded-java/resources/tree/main/Ch03) - Java 17
* [Ch04](https://github.com/well-grounded-java/resources/tree/main/Ch04) - Class files and bytecode
* [Ch05](https://github.com/well-grounded-java/resources/tree/main/Ch05) - Java concurrency fundamentals
* [Ch06](https://github.com/well-grounded-java/resources/tree/main/Ch06) - JDK concurrency libraries
* [Ch07](https://github.com/well-grounded-java/resources/tree/main/Ch07) - Understanding Java performance
* [Ch09](https://github.com/well-grounded-java/resources/tree/main/Ch09) - Kotlin
* [Ch10](https://github.com/well-grounded-java/resources/tree/main/Ch10) - Clojure
* [Ch11](https://github.com/well-grounded-java/resources/tree/main/Ch11) - Building with Maven & Gradle
* [Ch11](https://github.com/well-grounded-java/resources/tree/main/Ch12) - Running Java in containers
* [Ch13](https://github.com/well-grounded-java/resources/tree/main/Ch13) - Testing fundamentals
* [Ch14](https://github.com/well-grounded-java/resources/tree/main/Ch14) - Testing beyond JUnit
* [Ch15](https://github.com/well-grounded-java/resources/tree/main/Ch15) - Advanced functional programming
* [Ch16](https://github.com/well-grounded-java/resources/tree/main/Ch16) - Advanced concurrent programming
* [Ch17](https://github.com/well-grounded-java/resources/tree/main/Ch17) - Modern internals
* [Ch18](https://github.com/well-grounded-java/resources/tree/main/Ch18) - Future Java

## Running the Examples

Examples throughout this repo come in three forms:

* Maven projects
* Gradle projects
* Simple examples with loose code files

Maven and Gradle projects should be ready to build `mvn compile` or
`./gradlew build`. Individual example README files will contain more precise
instructions for running from the command-line, as the details vary per project.

Examples with loose code files may be either compiled individually or loaded
in an IDE. See the example README files for more precise instructions for each
case. You may need to set the SDK version for the project.

:warning: We don't recommend loading this entire project from the root.
The mixture of builds, JDK versions and loose files doesn't tend to work well
together. Any individual `Ch*` location should be loadable, though, even when it
contains multiple projects.
