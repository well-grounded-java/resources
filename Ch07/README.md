# Ch07 - Understanding Java performanc

## `Ch07/ch07`

This directory contains an example for understanding the interactions of our
code's performance with the L1 cache. These example should be used with Java 11+.

* `CacheTester.java` - Example running code in various ways to hit or miss the L1 cache.

This example may be loaded and run in an IDE at the `Ch07` level. You may need
to set the project JDK version.

To compile and run these samples at the command-line, do the following:

```
cd Ch07/ch07
javac *.java
java -cp .. ch07.CacheTester
```
