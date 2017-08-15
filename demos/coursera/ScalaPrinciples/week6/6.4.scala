object Main extends App {

  val romanNumerals: Map[String, Int] = Map("I" -> 1, "V" -> 5, "X" -> 10)
  val capitalOfCountry: Map[String, String] = Map("US" -> "Washington", "Switzerland" -> "Bern")
  println(capitalOfCountry("US"))

  // Avoid exception
  capitalOfCountry get "andorra"

  val fruit = List("apple", "pear", "orange", "pineapple")
  fruit sortWith (_.length < _.length)
  fruit.sorted

  println(fruit groupBy (_.head))

  class Poly(val terms: Map[Int, Double]) {
    def + (other: Poly) = new Poly(terms ++ (other.terms map adjust))

    def adjust(term: (Int, Double)): (Int, Double) = {
      val (exp, coeff) = term
      terms get exp match {
        case Some(coeff1) => exp -> (coeff + coeff1)
        case None => exp -> coeff
      }
    }

    override def toString =
      (for((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
  }

  // Better way of Poly class
  class PolyAlt(terms0: Map[Int, Double]) {
    def this(bindings: (Int, Double)*) = this(bindings.toMap)
    val terms = terms0 withDefaultValue 0.0 // Give default values to empty values

    def + (other: PolyAlt) = new PolyAlt((other.terms foldLeft terms)(addTerm))

    def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
      val (exp, coeff) = term
      terms + (exp -> (coeff + terms(exp)))
    }

    // Alternative of adjust not used here any more
    // def adjust(term: (Int, Double)): (Int, Double) = {
    //   val (exp, coeff) = term
    //   exp -> (coeff + terms(exp))
    // }

    override def toString =
      (for((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
  }

  val p1 = new Poly(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)) //  6.2x^5 + 4.0x^3 + 2.0x
  val p2 = new Poly(Map(0 -> 3.0, 3 -> 7.0)) // 7.0x^3 + 3
  println(p1)
  println(p2)
  println(p1 + p2) //2.0x^1 + 7.0x^3 + 6.2x^5 + 3.0x^0

  val p3 = new PolyAlt(1 -> 2.0, 3 -> 4.0, 5 -> 6.2) //  6.2x^5 + 4.0x^3 + 2.0x
  val p4 = new PolyAlt(0 -> 3.0, 3 -> 7.0) // 7.0x^3 + 3
  println(p3)
  println(p4)
  println(p3 + p4) //2.0x^1 + 7.0x^3 + 6.2x^5 + 3.0x^0

  val intList = List(1,2,3)
  println(intList.foldLeft(12)((b,a) => b+a))
  println(intList.foldLeft(1)((b,a) => b*a))
  println(intList.foldLeft(List[Int]())((b,a) => a :: b))
}
