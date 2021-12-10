# Ch05 - Java concurrency fundamentals

## `Ch05/ch05`

This directory contains a few separate examples. These have no external
dependencies, but may require multiple classes from within the directory.
These examples should be used with Java 11+.

* `BadThread.java` - Example of uncaught exception handlers from a `Thread`.
* `FSOMain.java` - Concurrent account transfer example. Uses multiple classes in the directory.
* `LifecycleWithInterrupt.java` - Example of `Thread.interrupt` clearing state.
* `SafeLifecycleWithInterrupt.java` -  Example of capturing when thread was interrupted.
* `SimpleLifecycle.java` - Example of basic thread lifecycle.

These examples may be loaded and run in an IDE. You may need to set the project
JDK version.

To compile and run these samples at the command-line, do the following:

```
cd Ch05/ch05
javac *.java
java -cp .. ch05.SimpleLifecycle
```
