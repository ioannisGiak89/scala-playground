object Main extends App {
  val zero = Zero
  var newS = new Succ(zero)
  println(newS.successor);
}

abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat = new Succ(this)
  def + (that: Nat): Nat
  def - (that: Nat): Nat
  override def toString: String
}

object Zero extends Nat {
  def isZero = true
  def predecessor = throw new Error("0.predecessor")
  def + (that: Nat) = that
  def - (that: Nat) = if (that.isZero) this else throw new Error("- on 0")
  override def toString = "{" + 0 + "}"

}

class Succ(n: Nat) extends Nat {
  def isZero = false
  def predecessor = n
  def + (that: Nat) = new Succ(that + n)
  def - (that: Nat) = if (that.isZero) this else n - that.predecessor
  override def toString = "{" + this.n + "}"
}
