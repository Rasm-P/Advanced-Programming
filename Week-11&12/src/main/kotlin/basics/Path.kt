package basics

sealed class Path<T> : Iterable<T> {
    class ValuePath<T>(val value: T, val rest: Path<T>) : Path<T>() {
        override fun iterator(): Iterator<T>  = iterator {
            yield(value)
            yieldAll(rest)
        }
    }
    class EmptyPath<T> : Path<T>() {
        override fun iterator(): Iterator<T> = iterator { }
    }
}

fun main() {
    val names = Path.ValuePath("Anders", Path.ValuePath("Bente", Path.ValuePath("Christine", Path.EmptyPath())))
    for (name in names) println(name)
}