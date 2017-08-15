import scala.io.Source

// import java.util.Calendar
object Main extends App {

  val in = Source.fromURL("http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxwords.txt")
  val words = in.getLines.toList filter (word => word forall (chr => chr.isLetter))
  val mnem = Map(
    '2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
    '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ"
  )

  // Invert the map to give a map from chars 'A' ... 'Z' to '2' ... '9'
  val charCode: Map[Char, Char] =
    for {
      (digit, str) <- mnem
      ltr <- str
    } yield ltr -> digit

  // Maps a word to the digit string it can represent, e.g. "Java" -> "5282"
  def wordCode(word: String): String =
    word.toUpperCase map charCode

  // A map from digit strings to the words that represent them,
  // e.g. "5282" -> List("Java", "Kata", "Lava", ...)
  // Note: A missing number should map to the empty set, e.g "1111" -> List()
  def wordsForNum: Map[String, Seq[String]] = {
    words groupBy wordCode withDefaultValue Seq()
  }

  // Return all ways to encode a number as a list of words
  def encode(number: String): Set[List[String]] =
    if (number.isEmpty) Set(List())
    else {
      for {
        split <- 1 to number.length
        word <- wordsForNum(number take split)
        rest <- encode(number drop split)
      } yield word :: rest
    }.toSet

  def translate(number: String): Set[String] =
    encode(number) map (_ mkString " ")

  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + "ns")
    result
  }

  // time {encode("7225247386")}
   println(encode("7225247386"))
  //println(translate("7225247386"))
}
