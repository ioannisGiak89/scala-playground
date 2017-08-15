

object Test extends App {

  // some print lns
  // val a: Int = 10
  // val b: Boolean = true
  // val c: Char = 'a'
  // val d: Double = 0.23
  // val e: String = "Helllo"
  //
  // println("Int value " + a)
  // println("Bool value " + b)
  // println("Char value " + c)
  // println("Double value " + d)
  // println("String value " + e)

  // Tailrec sum and currying
  // def sum(f: Int => Int)(a: Int, b: Int): Int = {
  //   def loop(a: Int, acc: Int): Int = {
  //     if (a > b) acc
  //     else loop(a + 1, acc + f(a))
  //   }
  //   loop(a , 0)
  // }
  //
  // println(sum(x => x * x)(3, 5));
  // println(sum(x => x)(3, 5));
  // println(sum(x => x + x)(3, 5));

  // Tailrec product and currying
  // def product(f: Int => Int)(a: Int, b:  Int): Int =
  //   if (a > b) 1
  //   else f(a) * product(f)(a + 1, b)
  // println(product(x => x)(0, 100))
  //
  // def fact(n: Int) = product(x => x)(1, n)
  // println(fact(3))

  // Generic function for sum and product using currying
  // def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int =
  //   if (a > b) zero
  //   else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
  //
  // def product(f: Int => Int)(a: Int, b:  Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b)
  // def sum(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x + y, 0)(a, b)
  // println(product(x => x + x)(3, 4))
  // println(sum(x => x * x)(3, 5))

}
