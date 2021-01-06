// Named arguments
fun coordinates(x: Int, y: Int) {
  println("($x, $y)")
}

// These will print the same
coordinates(10, 20)
coordinates(y = 20, x = 10)
