object Main extends App {

  val n = 7

  val vendor = (1 until n) map (i =>
    (1 until i) map (j => (i, j))
  )

  println(vendor.flatten)

  val xs = List(7,2,3)
  val ys = List(1,5,6)

  def scalarProduct(xs: List[Int], ys: List[Int]): List[Int] =
    for ((x, y) <- xs zip ys) yield x * y

  println(scalarProduct(xs, ys))
}
