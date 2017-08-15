object course1_1 {
  case class Book(title: String , authors: List[String])

  val books: List[Book] = List(
    Book(
      title = "Structure and Interpretation of Computer Programs",
      authors = List(" Abelson, Harald", " Sussman, Gerald J.") 
    ),
    Book(
      title = "Introduction to Functional Programming",
      authors = List("Bird, Richard", "Wadler, Phil")
    ),
    Book(
      title = "Effective Java",
      authors = List("Bloch, Joshua")
    ),
    Book(
      title = "Java Puzzlers",
      authors = List("Bloch, Joshua", "Gafter, Neal")
    ),
    Book(
      title = "Programming in Scala",
      authors = List("Bloch, Odersky, Martin", "Spoon, Lex", "Venners, Bill")
    ),
    Book(
      title = "Effective Java 2",
      authors = List("Bloch, Joshua")
    )
  )

  for (b <- books; a <- b.authors if a startsWith "Bird,") yield b.title

//  for (b <- books; if b.title indexOf "Program" > 0) yield b.title

  // Get some author
  {for {
    b1 <- books
    b2 <- books
    if b1.title < b2.title
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield a1}.distinct

  // Make it Set instead of List to avoid duplicates
  val booksAlt: Set[Book] = Set(
    Book(
      title = "Structure and Interpretation of Computer Programs",
      authors = List(" Abelson, Harald", " Sussman, Gerald J.")
    ),
    Book(
      title = "Introduction to Functional Programming",
      authors = List("Bird, Richard", "Wadler, Phil")
    ),
    Book(
      title = "Effective Java",
      authors = List("Bloch, Joshua")
    ),
    Book(
      title = "Java Puzzlers",
      authors = List("Bloch, Joshua", "Gafter, Neal")
    ),
    Book(
      title = "Programming in Scala",
      authors = List("Bloch, Odersky, Martin", "Spoon, Lex", "Venners, Bill")
    ),
    Book(
      title = "Effective Java 2",
      authors = List("Bloch, Joshua")
    )
  )

  for {
    b1 <- booksAlt
    b2 <- booksAlt
    if b1.title < b2.title
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield a1

  for {
    books <- books
    a <- books.authors
    if a startsWith "Bird"
  } yield books.title

  // OR

//  books flatMap (b => b.authors withFilter (a => a startsWith "Bird") map (y => y.title))
}
