// if's are expressions
val iffy = if (1 == 1) {
  "sure"
} else {
  "nope"
}
println(iffy)

// one-liner - Kotlin's answer to the ternary ? : in most C-like languages
val myTurn = if (1 == 1) "sure" else "nope"
println(myTurn)

// when - Kotlin's answer to case statements
val x = 2
val w = when (x) {
  1 -> "one"
  2 -> "two"
  else -> "lots"
}

// when + in for collection inclusion
val valid = listOf(1, 2, 3)
val invalid = listOf(4, 5, 6)

val collected = when (x) {
  in valid   -> "valid"
  in invalid -> "invalid"
  else       -> "unknown"
}

// when + ranges
val ranged = when (x) {
  in 1..3 -> "valid"
  in 4..6 -> "invalid"
  else    -> "unknown"
}

// when + functions
fun theBest() = 1
fun okValues() = listOf(2, 3)

val message = when (x) {
  theBest()     -> "best!"
  in okValues() -> "ok!"
  else          -> "nope"
}

// try/catch as expression
fun dangerousCall(): Nothing = throw RuntimeException("boo")
val errorMessage = try {
  dangerousCall()
  "fine"
} catch (e: Exception) {
  "oops"
}
