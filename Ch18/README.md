# Chapter 18 - Future Java

## `Ch18/ch18`

This directory contains multiple examples. It may be loaded in IDE at the
`Ch18` level. Java 18+ is required.

* `ch18/AmberExamples.java` - Demonstrating further pattern matching from Project Amber
* `ch18/Java17Examples.java` - Demonstrating various new features of Java 17
* `ch18/SwitchExamples.java` - Switch expression examples

Alternatively, the samples may be compiled and run at the command-line as
follows:

```
cd Ch18/ch18
javac *.java
java -cp .. ch18.SwitchExamples
```

## `Ch18/Panama

This is one of the more complex examples in the book given it's use of native
libraries and the EA state of Panama.

* `Panama/src/main/java/org/libspng` - Example Panama generated wrappers for native library `libspng`
* `Panama/src/main/java/wgjd.png` - Our application using the generated wrappers for native library `libspng`

The wrappers were generated with an EA Panama build of JDK 17 (build
17-panama+3-167 (2022/1/18)) from https://jdk.java.net/panama/17/ and against
0.7.2 of `libspng`

### `libspng` installation

The project requires the native library `libspng` be installed. While this may
be installable with your package manager of choice, we recommend cloning from
GitHub and building locally to ensure everything lines up.

To clone the library and move to the expected version:

```
cd Ch18/Panama
git clone git@github.com:randy408/libspng.git
cd libspng
git checkout v0.7.2
```

Note that newer versions may differ from the generated wrappers, so if you
don't use `v0.7.2` then you'll need to run `jextract` rather than using the
checked in wrappers.

To build the library:

```
cd Ch18/Panama/libspng

mkdir cbuild
cd cbuild
cmake ..
make
```

We don't bother to `make install` to a system location as we'll use the library
directly where the build generated.

This may require installing `cmake` through your package manager if it is not
already available. Further instructions, options at are available at
https://github.com/randy408/libspng/blob/v0.7.2/docs/build.md:

### `jextract` generating wrappers

Generating wrapper classes yourself isn't required to build and run the
project if you're using 0.7.2 `libspng` as instructed above.

If you need a different version of just want to try doing it yourself, though,
as of this writing you'll need the Panama EA (https://jdk.java.net/panama/) to
get access to the `jextract` tool.

To rerun the extraction where this project expects it, do the following:

```
cd Ch18/Panama/src/main/java

jextract --source -t org.libspng \
  -I /Applications/Xcode.app/Contents/Developer/Platforms/MacOSX.platform/Developer/SDKs/MacOSX.sdk/usr/include \
  -l spng ../../../libspng/spng/spng.h
```

The `-I` for including system types may need to be altered to match your OS's
location for typical native headers.

Depending on your path settings, `jextract` may not be directly available to
your shell. If that's the case, you can run it with `$JAVA_HOME/bin/jextract`
when you're using the Panama EA JDK version.

### Building and running the app

If you aren't regenerating your wrappers, you should be able to build and run
the application as checked in on JDK 17+, without needing the Panama EA.

From the `Ch18/Panama` project root:

```
cd Ch18/Panama

./gradlew jar

java -jar build/libs/Panama.jar \
   --add-modules=jdk.incubator.foreign \
   --enable-native-access=ALL-UNNAMED \
   -Djava.library.path=./libspng/cbuild \
   ./libspng/tests/images/basi0g01.png
```

When run against a `png` file, you should see output indicating what type of image it is.

```
WARNING: Using incubator modules: jdk.incubator.foreign
File type: grayscale
```
