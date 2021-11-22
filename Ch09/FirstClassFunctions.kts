// Capturing as variables
fun checkCondition(n: Int) = n == 42
val check = ::checkCondition
check(42)

// Lambda syntax
val anotherCheck = { n: Int -> n > 100 }
anotherCheck(1000)
anotherCheck.invoke(1000)

// Function that takes another function as an argument
fun callsAnother(funky: (Int) -> Unit) {
  funky(42)
}

// Lambda with and without explicit type
callsAnother({ n: Int -> println("Got $n") })
callsAnother({ n -> println("Got $n") })

// Single-argument lambdas `it`
callsAnother({ println("Got $it") })
callsAnother() { println("Got $it") }
callsAnother { println("Got $it") }
