object Main extends App {

  def init[T](xs: List[T]): List[T] = xs match {
    case List() => throw new Error("init of empty list")
    case List(x) => List()
    case y :: ys => y :: init(ys)
  }

  def removeAt[T](n: Int, xs: List[T]) = (xs take n) ::: (xs drop n + 1)

  val list = List(7,2,3,4,1,5,6)

  println(removeAt(3, list))
}
