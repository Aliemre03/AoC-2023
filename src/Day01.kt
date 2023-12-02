val words = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
val reverseWords = words.map { it.reversed() }
val digits = List(10) { "$it" }
fun main() {
    //part1()
    part2()
}

fun part1() {
    val lines = readInput("Day01/input")
    println(lines.sumOf { line: String ->
        line.first { it.isDigit() }.digitToInt() * 10 + line.last { it.isDigit() }.digitToInt()
    })
}

fun part2() {
    val lines = readInput("input")
    println(lines.sumOf { line: String ->
        getNumber(line, words) * 10 + getNumber(line.reversed(), reverseWords)
    })
}

private fun getNumber(line: String, words: List<String>): Int {
    val (wordIndex, word) = line.findAnyOf(words) ?: (Int.MAX_VALUE to "not found")
    val (digitIndex, digit) = line.findAnyOf(digits) ?: (Int.MAX_VALUE to "not found")

    return if (digitIndex < wordIndex) {
        digit.toInt()
    } else {
        word.digitWordToInt(words)
    }
}

private fun String.digitWordToInt(wordList: List<String>): Int {
    val index = wordList.indexOf(this)
    if (index == -1) error("not a digit word")
    return index + 1
}
