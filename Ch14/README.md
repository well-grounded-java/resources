# Chapter 14 - Testing beyond JUnit

## `Ch14`

This directory contains multiple examples of other testing approaches beyond
the basics of JUnit.

This entire directory can be loaded in an IDE at the `Ch14` level, or each
subdirectory may be individually loaded itself. Java 11+ is required.

* `testcontainers` - Integration testing with testcontainers. Can be run with `./gradlew check`
* `spek-gradle` - Spek framework integrated with Gradle. Can be run with `./gradlew check`
* `spek-maven` - Spek framework integrated with Maven. Can be run with `mvn verify`

## `Ch14/clj-testing`

This example shows Clojure testing with the built-in framework and property
testing via `test.check`.

You'll need to [install Clojure](https://clojure.org/guides/getting_started) if
you haven't already before running this example. These resources have been
checked with version 1.10 of Clojure.

Tests may be run by the following:

```
cd Ch14/clj-testing
clj -M test/first_test.clj
```

This may occasionally fail with the message `Couldn't satisfy such-that
predicate after 100 tries.` which is an indication our property generation
could use some fine-tuning.

Additionally there are scripts for fancier Clojure test runners under the
`Ch14/clj-testing/bin` directory.
