import java.time.LocalDate
import java.time.LocalDateTime

open class Person(
  val birthdate: LocalDate,
  var name: String) {

  // Secondary constructor
  constructor(name: String)
    : this(LocalDate.of(0, 1, 1), name) {
  }

  init {
    if (birthdate.year < 2000) {
      println("So last century")
    }
    // println(nameParts)  // Not allowed because nameParts not initialized yet
  }

  val nameParts: List<String> = name.split(" ")

  init {
    println(nameParts)
  }

  open fun isBirthday(): Boolean {
    return today() == birthdate
  }

  private fun today(): LocalDate {
    return LocalDateTime.now().toLocalDate()
  }
}

val person = Person(LocalDate.of(1996, 1, 23), "Somebody Else")
println("Hi ${person.name}. You were born on ${person.birthdate}")

val someoneElse = Person("Someone Else")
println("Oh hi ${someoneElse.name}. You weren't really born ${someoneElse.birthdate}, were you?")

// Subclass of Person
class Child(birthdate: LocalDate, name: String)
  : Person(birthdate, name) {

  override fun isBirthday(): Boolean {
    val itsToday = super.isBirthday()
    if (itsToday) {
      println("YIPPY!!")
    }
    return itsToday
  }
}

val child = Child(LocalDateTime.now().toLocalDate(), "Born Today")
child.isBirthday()
