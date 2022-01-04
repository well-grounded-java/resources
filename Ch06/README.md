# Ch06 - JDK concurrency libraries

## `Ch06/ch06`

This directory contains a number of separate examples. These have no external
dependencies, but may require multiple classes from within the directory.
These examples should be used with Java 11+.

These examples may be loaded and run in an IDE at the `Ch06` level. You may
need to set the project JDK version.

* `accounts/Main.java` - Example of a concurrent account transfer system. Uses other classes from in the `accounts` directory.
* `CDLExamples.java` - Example usage of the `CountDownLatch` class
* `CFExamples.java` - Example usage of the `CompletableFuture` class
* `COWExamples.java` - Exampe usage of the `CopyOnWriteArrayList` class
* `ExecutorExamples.java` - Example usage of the `Executors.newSingleThreadExecutor` factory method
* `FutureExamples.java` - Example usage of the `Future<>` interface
* `RWLockExamples.java` - Example usage of the `ReentrantReadWriteLock` class

Several examples explore issues around concurrent access to `Map` implementations

* `BadMapExamples.java` - Example of bad results from unsafe concurrent access to `HashMap`.
* `Dictionary.java` - A basic, non-concurrent implementation of the `Map` interface.
* `ImmutableDictionary.java` - An immutable wrapper around our `Dictionary` class.
* `SynchronizedDictionary.java` - A naively synchronized wrapper around our `Dictionary` class.

To compile and run these samples at the command-line, do the following:

```
cd Ch06/ch06
javac *.java
java -cp .. ch06.BadMapExamples

cd Ch06/ch06/accounts
javac *.java
java cp ../.. ch06.accounts.Main
```
