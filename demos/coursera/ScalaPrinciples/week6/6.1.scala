object Main extends App {

  val xs = Array(7,2,3,4,1,5,6)
  println((xs map (x => x * 2)).deep.mkString(","))

  val s = "Hello World"
  println(s filter (c => c.isUpper))

  println(s exists (c => c.isUpper))
  println(s forall (c => c.isUpper))

  val pairs = List(1,2,3,7) zip s
  println(pairs);
  println(pairs.unzip);

  println(s flatMap (c => List('.', c)))

  // Other usefull functions: max, min, sum, product

}
