object Main {

  def main(args: Array[String]): Unit = {
    println("=============== Task 1 ===============")
    val container = new Container[String]("Hello, World!")
    println(container.getContent)
    println(container.applyFunction((str: String) => "length: " + str.length))


    println("=============== Task 2 ===============")
    val no = new No
    val yes = new Yes[String]("s24068")
    println("object 'no' is subtype Maybe[_]: " + no.isInstanceOf[Maybe[_]])
    println("object 'yes' is subtype Maybe[_]: " + yes.isInstanceOf[Maybe[_]])


    println("=============== Task 3 ===============")
    val ExtendWithApplyFunctionForNo: ExtendWithApplyFunction[No] = new ExtendWithApplyFunction[No](new No())
    ExtendWithApplyFunctionForNo.applyFunction(a => a)
    println(ExtendWithApplyFunctionForNo.getContent)
    val ExtendWithApplyFunctionForYes: ExtendWithApplyFunction[Yes[String]] = new ExtendWithApplyFunction[Yes[String]](new Yes("s24068"))
    ExtendWithApplyFunctionForYes.applyFunction(a => new Yes("length: " + a.getContent.length))
    println(ExtendWithApplyFunctionForYes.getContent.getContent)


    println("=============== Task 4 ===============")
    val ExtendWithGetOrElseForNo: ExtendWithGetOrElse[No] = new ExtendWithGetOrElse[No](new No())
    println(ExtendWithGetOrElseForNo.getOrElse)
    val ExtendWithGetOrElseForYes: ExtendWithGetOrElse[Yes[String]] = new ExtendWithGetOrElse[Yes[String]](new Yes("s24068"))
    println(ExtendWithGetOrElseForYes.getOrElse)
  }
}
//--------------- Task 1 ---------------//
class Container[A](private val value: A) {
  def getContent: A = value
  def applyFunction[R] (fn: A => R) : R = fn(value)
}
//--------------- Task 2 ---------------//
trait Maybe[A]
class No extends Maybe[Nothing]
class Yes[A](value: A) extends Maybe[A] {
  private val _value: A = value
  def getContent: A = _value
}
//--------------- Task 3 ---------------//
class ExtendWithApplyFunction[A](value: A) {
  private var _value: A = value
  def getContent: A = _value
  def applyFunction(function: A => A): A = {
    function(_value) match {
      case _: No =>
        _value
      case _: Yes[_] =>
        _value = function(_value)
        _value
      case _ =>
        null.asInstanceOf[A]
    }
  }
}
//--------------- Task 4 ---------------//
class ExtendWithGetOrElse[A](value: A) {
  private val _value: A = value
  def getContent: A = _value
  def getOrElse[B]: B = {
    _value match {
      case _: No =>
        "class No with no content".asInstanceOf[B]
      case _: Yes[_] =>
        _value.asInstanceOf[Yes[A]].getContent.asInstanceOf[B]
      case _ =>
        null.asInstanceOf[B]
    }
  }
}
