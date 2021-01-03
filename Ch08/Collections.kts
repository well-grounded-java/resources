// Creating collections
val readOnlyList = listOf("a", "b", "c")
val mutableList  = mutableListOf("a", "b", "c")

val readOnlyMap = mapOf("a" to 1, "b" to 2, "c" to 3)
val mutableMap  = mutableMapOf("a" to 1, "b" to 2, "c" to 3)

val readOnlySet = setOf(0, 1, 2)
val mutableMap  = mutableSetOf(1, 2, 3)

// Iterating, mapping and filtering
val l = listOf("a", "b", "c")

for (s in l) {
  println(s)
}

val mapped = l.map { it.toUpperCase() }
println(mapped)

val filtered = l.filter { it != "b" }
println(filtered)

val all  = l.all  { it.length == 1 }
println(all)

val any  = l.any  { it.length == 2 }
println(any)

val none = l.none { it == "a" }
println(none)

val resultWith = l.associateWith { it.length }
println(resultWith)

val resultBy   = l.associateBy   { it.length }
println(resultBy)
