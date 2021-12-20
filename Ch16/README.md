# Chapter 16 - Advanced concurrent programming

## `Ch16/ch16`

This directory contains examples of various concurrent programming concepts.

You can load at the `Ch16` directory in an IDE to run these examples. Java 11+
is required.

* `ch16/SorterMain.java` - Demonstrating parallel sorting on the `ForkJoinPool`
* `ch16/CFExamples.java` - CompletableFuture examples

Alternatively, the samples may be compiled and run at the command-line as
follows:

```
cd Ch16/ch16
javac *.java
java -cp .. ch16.SorterMain
```

## `Ch16/coroutine-app`

This directory contains an example Kotlin application walking through some of
the basics of coroutine launching.

It may be loaded either with the entire `Ch16` directory, or directly itself in
an IDE. It requires Kotlin be installed.

See `Ch16/coroutines-app/coroutines-app.bytecode` for the decompiled bytecode
of a coroutine app.
