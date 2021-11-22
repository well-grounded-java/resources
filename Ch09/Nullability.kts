// Won't compile for your (null)  safety!
// val i: Int = null
// val s: String = null

val i: Int? = null
val s: String? = null

// Won't compile because of null safety checks
// println(s.length)

// Compiles, and returns null if `s` is null
println(s?.length)

// Compiles, but will raise exception at runtime
// println(s!!.length)

// Compiles happily thanks to smart casting
if (s != null) {
  println(s.length)
}

data class Node(val parent: Node?, val value: String)

// Chaining ?.
val node = Node(null, "only one")
println(node.parent?.parent?.parent)
