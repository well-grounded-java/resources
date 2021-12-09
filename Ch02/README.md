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

## 
