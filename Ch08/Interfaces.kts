import java.time.LocalDate

interface Greetable {
  fun greet(): String
}

open class Person constructor(
  val birthdate: LocalDate,
  var name: String): Greetable {

  override fun greet(): String {
    return "Hello there"
  }
}

val greet : Greetable = Person(LocalDate.of(1996, 1, 23), "Some Body")
println(greet.greet())
