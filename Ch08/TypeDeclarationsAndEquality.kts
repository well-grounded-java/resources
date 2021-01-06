// Inferred types
var i1: Int = 1
var s1: String = "String"

// Declaring types
var i2: Int = 1
var s2: String = "String"
//var n: Int = "error"   // Won't compile because of type mismatch

// More natural equality
var s: String = "A " + "value"
if (s == "A value") {
    println("They match!")
}
