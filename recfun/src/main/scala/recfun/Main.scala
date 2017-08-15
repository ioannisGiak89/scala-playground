package recfun

import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {


    def factorial(x: Int): Int = {
      @tailrec
      def loop(acc: Int, n: Int): Int =
        if (n == 0) acc
        else loop(acc * n, n - 1)

      loop(1, x)
    }

    if (c == 0 || r == 0 || r == 1 || c == r) {
      1
    } else  {
      factorial(r) / (factorial(c) * factorial(r - c))
    }
  }
  
  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {


    var expectClosingParentheses: Int = 0

    def loop(char: Char, l: List[Char]): Boolean = {

      var closedWithNoExpectation : Boolean = false

      if (char == '(') {
        expectClosingParentheses = expectClosingParentheses + 1
      } else if (char == ')') {
        if (expectClosingParentheses == 0) {
          closedWithNoExpectation = true
        } else {
          expectClosingParentheses = expectClosingParentheses - 1
        }
      }

      if (l.isEmpty) {
        !closedWithNoExpectation && expectClosingParentheses == 0
      } else {
        loop(l.head, l.tail)
      }
    }

    loop(chars.head, chars.tail)
  }
  
  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {

    def calulateChangeCombinations(money: Int, coins: List[Int]): Int = {

      if (money == 0) 1
      else if (money < 0 || coins.isEmpty) 0
      else {
        calulateChangeCombinations(money - coins.head, coins) +
          calulateChangeCombinations(money, coins.tail)
      }
    }

    if (money == 0) 0
    else calulateChangeCombinations(money, coins)
  }

  }
