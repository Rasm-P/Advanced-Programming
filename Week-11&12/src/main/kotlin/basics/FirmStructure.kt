package basics

import java.time.LocalDate

open class Person(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate) {
    override fun toString() = "$firstName $lastName is born $dateOfBirth"
}

class Employee(
    firstName: String, lastName: String, dateOfBirth: LocalDate,
    val dateOfEmployment: LocalDate,
    val salary: Int,
    department: Department
) : Person(firstName, lastName, dateOfBirth) {
    var department: Department = department
        get() = field
        set(value) {
            field.employeeList.remove(this)
            field = value
            field.employeeList.add(this)
        }
    init {
        this.department.employeeList.add(this)
    }
}

class Department(
    val code: String, val name: String
) {
    lateinit var manager: Employee
    internal val employeeList = mutableListOf<Employee>()
    val employees : List<Employee> get() = employeeList

}

//class Employee(*, val dateOfEmployment: LocalDate, val salary: Int)
//    : Person(*)

fun main() {
    val adm = Department("ADM", "Administration")
    val kurt = Employee("Kurt", "Hansen", LocalDate.of(1990, 2, 28), LocalDate.of(2016, 12, 24), 50_000_00, adm)
    adm.manager = kurt
    val it = Department("IT", "Information Tech")
    it.manager = kurt
    println("Hello ${kurt.firstName} from ${kurt.dateOfBirth}")
    println("ADM: ${adm.code} is ${adm.name} has ${adm.manager} as manager")
    println("IT: ${it.code} is ${it.name} has ${it.manager} as manager")

    val sonja = Employee("Sonja", "Jensen", LocalDate.of(1992, 4, 6), LocalDate.of(2018, 11, 14), 35_000_00, adm)
    val ib = Employee("Ib", "Bo", LocalDate.of(1959, 11,14), LocalDate.of(1980, 10, 1), 40_000_00, adm)

    println("Sonja: $sonja ${sonja.salary} ${sonja.department.name}")
    println("ADM: ${adm.code} ${adm.name}:")
    for (employee in adm.employees) {
        println("  $employee")
    }
    println("IT: ${it.code} ${it.name}:")
    for (employee in it.employees) {
        println("  $employee")
    }

    sonja.department = it

    println("ADM: ${adm.code} ${adm.name}:")
    for (employee in adm.employees) {
        println("  $employee")
    }
    println("IT: ${it.code} ${it.name}:")
    for (employee in it.employees) {
        println("  $employee")
    }

    // it.employees.clear()

    println("ADM: ${adm.code} ${adm.name}:")
    for (employee in adm.employees) {
        println("  $employee")
    }
    println("IT: ${it.code} ${it.name}:")
    for (employee in it.employees) {
        println("  $employee")
    }
}