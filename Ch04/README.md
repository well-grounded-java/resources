# Ch04 - Class files and bytecode

## `Ch04`

This directory contains individual files which may be compiled without needing
additional dependencies. These examples should be used with Java 11+.

* `ExampleNoClassDef.java` - Example of causing failure during class loading
* `LoadSomeClasses.java` - Example of custom classloader
* `Main.java` - Example of dynamically retrieving a `Class` object
* `NativeMethodChecker.java` - Example of custom classloader reading from file

These examples may be loaded and run individually in an IDE. You may need to
set the project JDK version. `LoadSomeClasses.java` and
`NativeMethodChecker.java` both expect to be passed arguments when executed.

To compile and run these samples at the command-line, do the following:

```
cd Ch04
javac *.java
java ExampleNoClassDef
```
