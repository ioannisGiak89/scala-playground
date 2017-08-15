
object test {

  val f: String => String = {
    case "ping" => "pong"
  }

  f("ping")
  //  f("abc") <- scala.MatchError: abc (of class java.lang.String)

  val f1: PartialFunction[String, String] = {
    case "ping" => "pong"
  }
  f1.isDefinedAt("ping")
  f1.isDefinedAt("abs")
  f1.isDefinedAt("pong")

  val l1: List[Int] = List(1, 2, 3)
  val l2: List[Int] = List(1, 5)
  val l3: List[Any] = l1 :: l2
  val l4: List[Int] = l1 ++ l2
  println(l3)
  println(l4)

  for {
    i <- 0 to 3
    j <- 0 to 3
    if i + j >= 4
  } yield (i, j)
 }
