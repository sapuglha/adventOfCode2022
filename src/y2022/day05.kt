package y2022

import readFileAsLines

fun main() {

    /*
    [M]                     [N] [Z]
    [F]             [R] [Z] [C] [C]
    [C]     [V]     [L] [N] [G] [V]
    [W]     [L]     [T] [H] [V] [F] [H]
    [T]     [T] [W] [F] [B] [P] [J] [L]
    [D] [L] [H] [J] [C] [G] [S] [R] [M]
    [L] [B] [C] [P] [S] [D] [M] [Q] [P]
    [B] [N] [J] [S] [Z] [W] [F] [W] [R]
    1   2   3   4   5   6   7   8   9
     */
    fun initializeStack(): List<ArrayDeque<String>> = listOf(
        ArrayDeque<String>().apply { addAll(listOf("B", "L", "D", "T", "W", "C", "F", "M")) },
        ArrayDeque<String>().apply { addAll(listOf("N", "B", "L")) },
        ArrayDeque<String>().apply { addAll(listOf("J", "C", "H", "T", "L", "V")) },
        ArrayDeque<String>().apply { addAll(listOf("S", "P", "J", "W")) },
        ArrayDeque<String>().apply { addAll(listOf("Z", "S", "C", "F", "T", "L", "R")) },
        ArrayDeque<String>().apply { addAll(listOf("W", "D", "G", "B", "H", "N", "Z")) },
        ArrayDeque<String>().apply { addAll(listOf("F", "M", "S", "P", "V", "G", "C", "N")) },
        ArrayDeque<String>().apply { addAll(listOf("W", "Q", "R", "J", "F", "V", "C", "Z")) },
        ArrayDeque<String>().apply { addAll(listOf("R", "P", "M", "L", "H")) },
    )

    data class Instructions(
        val quantity: Int,
        val origin: Int,
        val destination: Int,
    )

    fun getInstructions(input: String): Instructions =
        Instructions(
            quantity = input.substringAfter("y2022.move ").substringBefore(" from ").toInt(),
            origin = input.substringAfter("from ").substringBefore(" to").toInt(),
            destination = input.substringAfter("to ").toInt(),
        )

    fun part1(input: List<String>): String = initializeStack()
        .apply {
            input.forEach { line ->
                val instructions = getInstructions(line)

                repeat(instructions.quantity) {
                    this[instructions.destination - 1].add(
                        this[instructions.origin - 1].removeLast()
                    )
                }
            }
        }
        .joinToString(separator = "") {
            it.last()
        }


    fun part2(input: List<String>): String = initializeStack()
        .apply {
            input.forEach { line ->
                val instructions = getInstructions(line)

                val tempDeque: ArrayDeque<String> = ArrayDeque()
                repeat(instructions.quantity) {
                    tempDeque.add(
                        this[instructions.origin - 1].removeLast()
                    )
                }
                this[instructions.destination - 1].addAll(tempDeque.reversed())
            }
        }
        .joinToString(separator = "") {
            it.last()
        }

    "y2022/data/day05".readFileAsLines().let { input ->
        println("part1: ${part1(input)}")
        println("part2: ${part2(input)}")
    }
}
