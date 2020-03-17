package basics

class LinkedList<T>(val value: T, val rest: LinkedList<T>? = null) : Iterable<T> {

    fun print() {
        println(value)
        rest?.print()
    }

    override fun iterator(): Iterator<T> {
        return PathIterator(this)
    }

    class PathIterator<T>(var step: LinkedList<T>?) : Iterator<T> {

        override fun hasNext(): Boolean {
            return step != null
        }

        override fun next(): T {
            val result = step!!.value // Brug ikke uden en voksen
            // val result = step?.value
            step = step?.rest
            return result
        }

    }

}


fun main() {
    val names = LinkedList("Anders", LinkedList("Bente", LinkedList("Christine")))
    for (name in names) println(name)
    names.print()
    // print(names)
}