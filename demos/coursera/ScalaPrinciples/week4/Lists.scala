object Main extends App {
  def isort(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case y :: ys => insert(y, isort(ys))
  }

  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => if (y >= x) x :: xs else y :: insert(x, ys)
  }

  val list = 10 :: 3 :: 7 :: 1 :: 4 :: Nil
  println(isort(list));
}
