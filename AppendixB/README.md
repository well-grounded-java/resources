# Appendix B - Recap of streams in Java 8

## `AppendixB`

This directory contains individual files which may be compiled without
external dependencies. These examples should be used with Java 11+.

* `StringStream.java` - Basic streaming filter and collect example
* `OtterApp.java` - Example application using custom classes. Needs `Otter.java` and `Keeper.java` compiled.
* `Otter.java` - Class definition for use in `OtterApp.java`
* `Keeper.java` - Class definition for use in `OtterApp.java`

These examples may be loaded and run individually in an IDE. You may need to
set the project JDK version.

To compile and run these samples at the command-line, do the following:

```
cd AppendixB
javac *.java
java StringStream
```
