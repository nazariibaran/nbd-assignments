package assignment7


object Main extends App {
  /**
   * Get day status
   */
  def getDayStatus(day: String): String = day.toUpperCase match {
    case "MONDAY" => "Work"
    case "TUESDAY" => "Work"
    case "WEDNESDAY" => "Work"
    case "THURSDAY" => "Work"
    case "FRIDAY" => "Work"
    case "SATURDAY" => "Weekend"
    case "SUNDAY" => "Weekend"
    case _ => "No such day"
  }

  println("Monday: " + getDayStatus("Thursday"))
  println("Venusday: " + getDayStatus("Venusday"))
  println("SATURDAY: " + getDayStatus("SATURDAY"))

  /**
   * Bank account class
   */
  class BankAccount() {
    private var balance: Double = 0

    def getBalance = {
      balance
    }

    def this(balance: Double) {
      this()
      this.balance = balance
    }

    def deposit(value: Double) {
      balance += value
      println(value + " deposit.")
    }

    def withdraw(value: Double) {
      if (value <= balance) {
        balance -= value
        println(value + "$ withdrew")
      } else {
        println("Not enough funds (" + value + ").")
      }
    }
  }

  var bankAccount = new BankAccount()
  bankAccount.deposit(150000)
  println(("Balance: " + bankAccount.getBalance))
  bankAccount.withdraw(12345)
  println(("Balance: " + bankAccount.getBalance))

  /**
   * Person class
   */
  case class Person(firstName: String, lastName: String)

  def patternMatching(person: Person) = person match {
    case Person("Nazarii", "Baran") => "Hey Nazarii"
    case Person("Angela", "Merkel") => "Guten Tag frau Merkel!"
    case Person(_, "Jackman") => "Oi, Hugh!"
    case default => "Hey, how are you doing?"
  }

  var person1 = new Person("Nazarii", "Baran")
  var person2 = new Person("Angela", "Merkel")
  var person3 = new Person("Hugh", "Jackman")
  var person4 = new Person("Chris", "Hemsworth")

  var listPerson = List[Person](person1, person2, person3, person4);
  for (person <- listPerson) {
    println(person.firstName + " " + person.lastName + ": " + patternMatching(person))
  }

  /**
   * Function with parameters - integer and a function operating on integers
   */
  def threeTimes(fn: Int => Int, n: Int): Int = fn(fn(fn(n)))

  println("ThreeTimes:", threeTimes(x => x + x * x, 2))

  /**
   * Student / Teacher / Employee
   */
  class Person2() {
    private var _firstName: String = ""
    private var _lastName: String = ""
    private var _taxToPay: Double = 0

    def this(firstName: String, lastName: String) {
      this()
      this._firstName = firstName
      this._lastName = lastName
      this._taxToPay = 0
    }

    def firstName: String = {
      _firstName
    }

    def lastName: String = {
      _lastName
    }

    def taxToPay: Double = {
      _taxToPay
    }
  }

  trait Student extends Person2 {
    override def taxToPay: Double = 0.0
  }

  trait Employee extends Person2 {
    private var _salary: Double = 0

    private def setSalary(value: Double) {
      _salary = value
    }

    private def getSalary: Double = if (_salary > 0) {
      _salary * taxToPay
    } else {
      0
    }

    override def taxToPay: Double = 0.20
  }

  trait Teacher extends Employee {
    override def taxToPay: Double = 0.10
  }

  val person = new Person2("Nazarii", "Baran")
  println("Person's tax: " + (person.taxToPay * 100) + "% of salary;")

  val student = new Person2("Franka", "Gernot") with Student
  println("Student's tax: " + (student.taxToPay * 100) + "% of salary;")

  val employee = new Person2("Susann", "Olaf") with Employee
  println("Employee's tax: " + (employee.taxToPay * 100) + "% of salary;")

  val teacher = new Person2("Hilbert", "Gabriela") with Teacher
  println("Teacher's tax: " + (teacher.taxToPay * 100) + "% of salary;")

  val teacherEmployee = new Person2("Vilma", "Kurt") with Teacher with Employee
  println("Teacher-Employee's tax: " + (teacherEmployee.taxToPay * 100) + "% of salary;")

  val employeeTeacher = new Person2("Oskar", "Constanze") with Employee with Teacher
  println("Employee-Teacher tax: " + (employeeTeacher.taxToPay * 100) + "% of salary;")
}
