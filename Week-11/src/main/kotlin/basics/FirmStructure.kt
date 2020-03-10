package basics

import java.time.LocalDate

open class Person(val firstName: String, val lastName: String, val dateOfBirth: LocalDate)

class Employee(firstName: String,lastName: String,dateOfBirth: LocalDate, val dateOfEmployment: LocalDate, val salary: Int, val department: Department) : Person(firstName, lastName, dateOfBirth) {

}

class Department(val code: String, val Name: String, val manager: Employee) {
    val employees = mutableListOf<Employee>()
}

fun main() {
    val kurt = Person("Kurt","Whonegut", LocalDate.of(1990,2,28))
    println("hello ${kurt.firstName} from ${kurt.dateOfBirth}")
}