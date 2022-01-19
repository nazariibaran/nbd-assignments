package assignment7

import scala.collection.immutable.Nil.:::
import scala.math.abs

object Main extends App {
  var days = List("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

  /**
   * For loop days comma-separated
   */
  def daysForLoop(list: List[String]): String = {
    var days = ""
    for (day <- list)
      if (day != list.last)
        days += s"$day, "
      else
        days += s"$day"
    days
  }

  println(daysForLoop(days))

  /**
   * For loop days comma-separated starts with s
   */
  def daysForLoopStartsWithS(list: List[String]) = {
    var days = ""
    for (
      day <- list if day.startsWith("S")
    ) if (day != list.last) days += s"$day, " else days += s"$day"
    days
  }

  println(daysForLoopStartsWithS(days))

  /**
   * While loop days comma-separated
   */
  def daysWhileLoop(list: List[String]): String = {
    var days = ""
    var i = 0
    while (i < list.length) {
      if (list(i) != list.last) days += s"${list(i)}, " else days += s"${list(i)}"
      i += 1
    }
    days
  }

  println(daysWhileLoop(days))

  /**
   * Days recursive
   */
    var newDays = ""

    def daysRecursive(index: Int): String = {
      var newI = index
      if (days(newI) != days.last) {
        newDays += s"${days(newI)}, "
        newI += 1
        daysRecursive(index)
      }
      else {
        newDays += s"${days(index)}"
      }
      newDays
    }: String

    println(daysRecursive(0))

  /**
   * Days recursive from last
   */
  def daysRecursiveFromLast(list: List[String]): String = {
    if (list.isEmpty) ""
    else list.last + ", " + daysRecursiveFromLast(list.splitAt(list.length - 1)._1)
  }

  println(daysRecursiveFromLast(days))

  /**
   * Days tail recursion
   */
  def daysTailRecursion(list: List[String]): String = {
    def step(day: List[String], result: String): String = {
      if (day.isEmpty) result
      else step(day.splitAt(1)._2, result + day.head + ", ")
    }

    step(list, "")
  }

  println(daysTailRecursion(days))

  /**
   * Days Fold left
   */
  def daysFoldL(list: List[String]): String = {
    list.foldLeft("") { (acc, i) =>
      if (i != list.last) s"$acc$i, " else s"$acc$i"
    }
  }

  println(daysFoldL(days))

  /**
   * Days Fold right
   */
  def daysFoldR(list: List[String]): String = {
    list.foldRight("") { (acc, i) =>
      if (acc != list.head) s", $acc$i" else s"$acc$i"
    }
  }

  println(daysFoldR(days))

  /**
   * Days Fold left
   */
  def daysFoldLStartsWithS(list: List[String]): String = {
    val filtered = list.filter(_.startsWith("S"))

    filtered.foldLeft("") { (acc, i) =>
      if (i != filtered.last) s"$acc$i, " else s"$acc$i"
    }
  }

  println(daysFoldLStartsWithS(days))

  /**
   * Collection Mapping
   */
  val products = Map("Bread" -> 10.0, "Sugar" -> 20.0, "Chocolate" -> 40.0)

  def collectionMap(list: Map[String, Double]): Map[String, Double] = {
    val reducedPrices = list.view.mapValues(_ * 0.9).toMap

    reducedPrices
  }

  println(collectionMap(products))

  /**
   * List mapping
   */
  var list = List(1, 2, 3, 4, 5)

  def listMap(list: List[Int]): List[Int] = {
    list map (_ + 1)
  }: List[Int]

  println(listMap(list))

  /**
   * List range
   */
  var list2 = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 11.5, 12, 13, -22, -1, 0, 127, 20)

  def listRange(list: List[Double]): List[Double] = {
    list.filter(_ <= 12).filter(_ >= -5).map(x => abs(x))
  }

  println(listRange(list2))

  /**
   * Tuple
   */
  def returnTuple() = {
    val tuple = (0, -5.5, "Nazarii")

    tuple.productIterator.foreach(println)
  }

  println(returnTuple())

  /**
   * Exclude 0
   */
  val list3 = List(0, 1, 2, 3, 4, 5, 6, 10, 20, 25, 100)

  def excludeZero(list: List[Int]): Unit = {
    if (list.isEmpty) List()
    else if (list.head != 0) List(list.head) ::: excludeZero(list.tail)
    else excludeZero(list.tail)
  }

  println(excludeZero(list3))

  /**
   * Options
   */
  def useOptions() = {
    // Example 1
    val some: Option[Int] = Some(15)
    val none: Option[Int] = None
    val xy = some.getOrElse(0)
    val yx = none.getOrElse(17)
    println(xy)
    println(yx)

    // Example 2
    val some1:Option[Int] = Some(20)
    val none1:Option[Int] = None
    val xx = some1.isEmpty
    val yy = none1.isEmpty
    println(xx)
    println(yy)
  }

  println(useOptions())
}
