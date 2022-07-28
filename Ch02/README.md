# Ch02 - Java modules

## `Ch02/src/ch02`

This directory contains individual files which may be compiled without needing
additional dependencies.

* `MyEncoder.java` - Compiles on Java 8, class unavailable on Java 11+
* `MyURLHandler.java` - Compiles on Java 8, module access disallowed on Java 11+
* `MyUnsafe.java` - Compiles on Java 8+, can be run directly
* `ReflBytecodeName.java` - Compiles on Java 8+, can be run directly

`MyUnsafe` and `ReflBytecodeName` may be run directly from in an IDE when loaded
at the `Ch02/src` level.

To compile and run them directly, you can do the following:

```
cd Ch02/src/ch02
javac *.java
java -cp .. MyUnsafe
java -cp .. ReflBytecodeName
```

## `wgjd.discovery`

This example demonstrates how to request and use access to internal modules.
It is is simplest to use from the command-line as it requires overrides that
by default your IDE won't have configured. It should be run using Java 11+.

```
# Commands expect to be run from Ch02 directory, _not_ wgjd.discovery
cd Ch02

# Compile allowing access to internal modules
javac -d out/wgjd.discovery \
  --add-exports=jdk.internal.jvmstat/sun.jvmstat.monitor=wgjd.discovery \
  wgjd.discovery/module-info.java \
  wgjd.discovery/wgjd/discovery/*.java \
  wgjd.discovery/wgjd/discovery/internal/*

# Run, allowing access to same internal modules we're accessing
java --module-path out \
  --add-exports=jdk.internal.jvmstat/sun.jvmstat.monitor=wgjd.discovery \
  -m wgjd.discovery/wgjd.discovery.Discovery
```

## `wgjd.multi-version`

This example demonstrates building a multi-release JAR file from scratch. It is
simplest to use via the command-line. It may be built using Java 11 but the
resulting JAR may be used on Java 8 as well.

```
# Expects to build within the following directory
cd Ch02/wgjd.multi-version

# Compile the classes for use with Java 8
javac --release 8 -d out src/main/java/wgjd2ed/*.java

# Compile the specialized classes for Java 11
javac --release 11 -d out-11 versions/11/java/wgjd2ed/GetPID.java

# Create the JAR file with class files for both 8 and 11
jar --create --file pid.jar --main-class=wgjd2ed.Main -C out/ . --release 11 -C out-11/ .

# Runs on either Java 8 or 11, indicates when run on Java 8 that it's doing so
java -jar pid.jar
```

## `wgjd.sitecheck`

This example creates a modular app from our Java 11+ HttpClient site checker in
chapter 1. This sample should be built on Java 11+.

```
# Expects to run from Ch02
cd Ch02

# Compile the example
javac -d out wgjd.sitecheck/module-info.java \
  wgjd.sitecheck/wgjd/sitecheck/*.java \
  wgjd.sitecheck/wgjd/sitecheck/concurrent/*.java \
  wgjd.sitecheck/wgjd/sitecheck/internal/*.java

# Run the example
java -cp out wgjd.sitecheck.SiteCheck http://github.com/well-grounded-java
```
