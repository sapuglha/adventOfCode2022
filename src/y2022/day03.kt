package y2022

import readFileAsLines
import java.lang.Exception

fun main() {
    fun Char.getCharValue(): Int =
        when (this) {
            in 'a'..'z' -> this - 'a' + 1
            in 'A'..'Z' -> this - 'A' + 27
            else -> throw Exception()
        }

    fun part1(input: List<String>): Int = input
        .map { line ->
            val (first, second) = line.chunked(line.length / 2)
            first.toSet()
                .intersect(second.toSet())
                .first()
                .getCharValue()
        }
        .sumOf { it }

    fun part2(input: List<String>): Int = input
        .chunked(3)
        .map { (line1, line2, line3) -> // deconstruct FTW
            line1
                .toSet()
                .intersect(line2.toSet())
                .intersect(line3.toSet())
                .first()
                .getCharValue()
        }
        .sumOf { it }

    "y2022/data/day03".readFileAsLines().let { input ->
        println("part1: ${part1(input)}")
        println("part2: ${part2(input)}")
    }
}
