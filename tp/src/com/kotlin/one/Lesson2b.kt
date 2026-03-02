package com.android.one

fun ex1CreateImmutableList(): List<Int> {
    return listOf(1, 2, 3, 4, 5)
}

fun ex2CreateMutableList(): MutableList<String> {
    val items = mutableListOf("Kotlin", "Java", "Swift")
    items.add("Dart")
    return items
}

fun ex3FilterEvenNumbers(): List<Int> {
    return (1..10).filter { it % 2 == 0 }
}

fun ex4FilterAndMapAges(ages: List<Int>): List<String> {
    return ages
        .filter { it >= 18 }
        .map { "Adult: $it" }
}

fun ex5FlattenList(): List<Int> {
    val nested = listOf(listOf(1, 2), listOf(3, 4), listOf(5))
    return nested.flatten()
}

fun ex6FlatMapWords(): List<String> {
    val phrases = listOf("Kotlin is fun", "I love lists")
    return phrases.flatMap { it.split(" ") }
}

fun ex7EagerProcessing(): Pair<List<Long>, Long> {
    val start = System.currentTimeMillis()
    val result = (1..1_000_000)
        .filter { it % 3 == 0 }
        .map { it.toLong() * it }
        .take(5)
    val end = System.currentTimeMillis()
    return result to (end - start)
}

fun ex8LazyProcessing(): Pair<List<Long>, Long> {
    val start = System.currentTimeMillis()
    val result = (1..1_000_000)
        .asSequence()
        .filter { it % 3 == 0 }
        .map { it.toLong() * it }
        .take(5)
        .toList()
    val end = System.currentTimeMillis()
    return result to (end - start)
}

fun ex9FilterAndSortNames(names: List<String>): List<String> {
    return names
        .filter { it.startsWith("A", ignoreCase = true) }
        .map { it.uppercase() }
        .sorted()
}

fun runtest() {
    check(ex1CreateImmutableList() == listOf(1, 2, 3, 4, 5)) { "ex1 failed" }
    check(ex2CreateMutableList() == mutableListOf("Kotlin", "Java", "Swift", "Dart")) { "ex2 failed" }
    check(ex3FilterEvenNumbers() == listOf(2, 4, 6, 8, 10)) { "ex3 failed" }
    check(ex4FilterAndMapAges(listOf(12, 18, 25, 16)) == listOf("Adult: 18", "Adult: 25")) { "ex4 failed" }
    check(ex5FlattenList() == listOf(1, 2, 3, 4, 5)) { "ex5 failed" }
    check(ex6FlatMapWords() == listOf("Kotlin", "is", "fun", "I", "love", "lists")) { "ex6 failed" }
    check(ex7EagerProcessing().first == listOf(9L, 36L, 81L, 144L, 225L)) { "ex7 failed" }
    check(ex8LazyProcessing().first == listOf(9L, 36L, 81L, 144L, 225L)) { "ex8 failed" }
    check(
        ex9FilterAndSortNames(listOf("Alice", "Bob", "amelia", "Charles", "Alex")) ==
            listOf("ALEX", "ALICE", "AMELIA")
    ) { "ex9 failed" }
}

fun main() {
    runtest()
    val eager = ex7EagerProcessing()
    val lazy = ex8LazyProcessing()

    println("All Lesson2b tests passed.")
    println("Ex7 eager result: ${eager.first} in ${eager.second} ms")
    println("Ex8 lazy result:  ${lazy.first} in ${lazy.second} ms")
}
