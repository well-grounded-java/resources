// Smart casting knows
val maybeNullString: String? = null
if (maybeNullString != null) {
  println(maybeNullString.length)
}

// Smart casting knows when we're a string
val anyString: Any = "Hello"
if (anyString is String) {
  println(anyString.toUpperCase())
}

// Smart casting works within the condition too
if (anyString is String && anyString.toUpperCase() == "HELLO") {
  println("Got something")
}

// Mutable properties (among other cases) can't use smart casting
class CantSmartCast(var str: Any = "boo") {
  fun checkIt() {
    if (str is String) {
      // Disallowed because `str` is a mutable property
      //println(str.toUpperCase())

      // We can still cast it by hand though
      val cast = str as String
      println(cast.toUpperCase())
    }
  }
}

CantSmartCast().checkIt()
