object Main extends App {

  def squareList(xs: List[Int]): List[Int] =
  xs match {
    case Nil => Nil
    case y :: ys => y * y :: squareList(ys)
  }

  def squareListMap(xs: List[Int]): List[Int] =
  xs map (x => x * x)

  val list = List(7,2,3,4,1,5,6)
  val fruits = List("orange", "apple", "banana", "pineapple")
  println(list,squareList(list))
  println(list,squareListMap(list))

  list filter (x => x > 0)
  list filterNot (x => x >0)
  list partition (x => x > 0)

  list takeWhile (x => x > 0)
  list dropWhile (x => x > 0)
  list span (x => x > 0)

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      val (first, rest) = xs span (y => y == x)
      first :: pack(rest)
  }

  println(
    pack(List("a", "a", "a", "b", "c", "c", "a")) == List(List("a", "a", "a"), List("b"), List("c", "c"), List("a"))
  )

  def encode[T](xs: List[T]): List[(T, Int)] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      pack(xs) map ( x => (x.head, x.length))
  }

  println(encode(List("a", "a", "a", "b", "c", "c", "a")));
}
