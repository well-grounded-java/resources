import java.time.LocalDate

class Person {
  val birthdate = LocalDate.of(1996, 1, 23)
  var name = "Name Here"
}

val person = Person()
println("Hi ${person.name}. You were born on ${person.birthdate}")
person.name = "Somebody Else"

// Fails to compile because val doesn't create a setter
// person.birthdate = LocalDate.of(2000, 1, 1)
