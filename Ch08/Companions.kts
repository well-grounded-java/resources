class ShyObject private constructor(val name: String) {

  companion object {
    fun create(name: String): ShyObject {
      return ShyObject(name)
    }
  }
}

ShyObject.create("The Thing")
