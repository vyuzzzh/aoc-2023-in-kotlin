package day1

import println
import readInput

val words = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { row -> selectNum(row) }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { row -> selectNumWithWord(row) }
    }

    val input = readInput("day1")
    part1(input).println()
    part2(input).println()
}

private fun selectNum(line: String): Int {
    val first = line.first() { it.isDigit() }
    val last = line.last() { it.isDigit() }

    return "${first}${last}".toInt()
}

private fun selectNumWithWord(line: String): Int {
    var f = Int.MAX_VALUE
    var l = Int.MIN_VALUE
    val firstIntIndex = line.indexOfFirst { it.isDigit() }
    val lastIntIndex = line.indexOfLast { it.isDigit() }
    var first = 0
    var last = 0

    for ((index, word) in words.withIndex()) {
        val firstIndexWord = line.indexOf(word)
        val lastIndexWord = line.lastIndexOf(word)

        if (firstIndexWord > -1 && firstIndexWord < f) {
            f = firstIndexWord
            first = index + 1
        }
        if (lastIndexWord > l) {
            l = lastIndexWord
            last = index + 1
        }
    }

    if (firstIntIndex > -1 && firstIntIndex < f) {
        first = line[firstIntIndex].digitToInt()
    }
    if (lastIntIndex > -1 && lastIntIndex > l) {
        last = line[lastIntIndex].digitToInt()
    }

    return first * 10 + last
}