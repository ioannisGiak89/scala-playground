//Why he doesn't always defines the return type

object rational extends App {
  val x = new Rational(1, 3)
  val y = new Rational(5, 7)
  val z = new Rational(3, 2)

  println(x.add(y))
  println(-x)
  println(x.sub(y).sub(z))
  println(y.add(y))
  println(x less y)
  println(x max z)

  println(new Rational(2))
  
  // Throws exception
  // val strange = new Rational(1, 0)
  // strange.add(strange)
}

class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be nonzero")

  def this(x: Int) = this(x,1)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  private val g = gcd(x, y)
  val numer = x / g
  val denom = y / g

  def less(that: Rational) = this.numer * that.denom < that.numer * this.denom
  def max(that: Rational) = if (this.less(that)) that else this

  def add(that: Rational) =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  // prefix -
  def unary_- : Rational = new Rational(-numer, denom)
  def sub(that: Rational) = this add -that
  override def toString = numer + "/" + denom
}
