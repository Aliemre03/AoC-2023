package Day02

import readInput

data class Cube(val color: String, val count: Int)
val targetCounts = mapOf("red" to 12, "green" to 13, "blue" to 14)

fun main() {

    part1()
    part2()
}

fun part1(){
    val input = readInput("Day02/input")

    val possibleGames = input.mapIndexedNotNull { index, game ->
        val cubesInGame = game.split(":")[1].split(";").flatMap { subset ->
            subset.trim().split(", ").mapNotNull { item ->
                val (count, color) = item.trim().split(" ")
                Cube(color, count.toIntOrNull() ?: return@mapNotNull null)
            }
        }

        if (cubesInGame.all { it.count <= targetCounts.getOrDefault(it.color, 0) }) index + 1 else null
    }
    println(possibleGames.sum())
}

fun part2(){
    val input = readInput("Day02/input")
    var totalPower = 0L

    for (game in input) {
        val cubesInGame = game.split(":")[1].split(";").flatMap { subset ->
            subset.trim().split(", ").map { item ->
                val (count, color) = item.trim().split(" ")
                Pair(color, count.toIntOrNull())
            }
        }

        val cubeCounts = mutableMapOf<String, Int>()
        for ((color, count) in cubesInGame) {
            cubeCounts[color] = maxOf(cubeCounts.getOrDefault(color, 0), count ?: 0)
        }

        val power = cubeCounts.values.fold(1L) { acc, value -> acc * value }
        totalPower += power
    }

    println(totalPower)

}