// Standard class, needs equality defined or reverts to reference
class PlainPoint(val x: Int, val y: Int)

val pl1 = PlainPoint(1, 1)
val pl2 = PlainPoint(1, 1)

println(pl1 == pl2)


// Data class, brings along property level equality
data class DataPoint(val x: Int, val y: Int)

val pd1 = DataPoint(1, 1)
val pd2 = DataPoint(1, 1)

println(pd1 == pd2)
