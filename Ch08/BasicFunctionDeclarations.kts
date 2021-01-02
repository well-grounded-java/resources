// Basic function definition
fun doTheThing(value: Int): Boolean {
  println("Done $value!")
  return true
}

doTheThing(42)

// Single-line definition
fun checkCondition(n: Int) = n == 42
checkCondition(1)
checkCondition(42)
