package y2022

import extensions.contains
import readFileAsLines

fun main() {
    fun getElves(line: String): Pair<IntRange, IntRange> {
        val (first, second) = line.split(",")
        val (firstBegin, firstEnd) = first.split("-").map(String::toInt)
        val (secondBegin, secondEnd) = second.split("-").map(String::toInt)

        return (firstBegin..firstEnd) to (secondBegin..secondEnd)
    }

    fun part1(input: List<String>): Int = input
        .map { getElves(it) }
        .map { (elf1, elf2) -> elf1.contains(elf2) || elf2.contains(elf1) }
        .count { it }

    fun part2(input: List<String>): Int = input
        .map { getElves(it) }
        .map { (elf1, elf2) -> (elf1.contains(elf2) || elf2.contains(elf1) || elf1.intersect(elf2).isNotEmpty()) }
        .count { it }

    "y2022/data/day04".readFileAsLines().let { input ->
        println("part1: ${part1(input)}")
        println("part2: ${part2(input)}")
    }
}
