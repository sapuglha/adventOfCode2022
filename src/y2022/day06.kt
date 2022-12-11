package y2022

import readFileAsLines

fun main() {


    fun part1(input: String): Int {
        val aux: ArrayDeque<Char> = ArrayDeque()
        var count = 0
        input
            .forEach {
                aux.addLast(it)
                count++

                // do we have at least 4?
                if (aux.size >= 4) {
                    // if there are duplicates, remove first
                    val hasDuplicates: Boolean =
                        aux[0] == aux[1] || aux[0] == aux[2] || aux[0] == aux[3]
                                || aux[1] == aux[2] || aux[1] == aux[3]
                                || aux[2] == aux[3]
                    if (hasDuplicates) {
                        aux.removeFirst()
                    } else {
                        return count
                    }
                }
            }
        return count
    }


    fun part2(input: String): Int {
        val aux: ArrayDeque<Char> = ArrayDeque()
        var count = 0
        input
            .forEach {
                aux.addLast(it)
                count++

                if (aux.size >= 14) {
                    // if there are duplicates, remove first
                    val hasDuplicates: Boolean =
                        aux[0] == aux[1] || aux[0] == aux[2] || aux[0] == aux[3] || aux[0] == aux[4] || aux[0] == aux[5] || aux[0] == aux[6] || aux[0] == aux[7] || aux[0] == aux[8] || aux[0] == aux[9] || aux[0] == aux[10] || aux[0] == aux[11] || aux[0] == aux[12] || aux[0] == aux[13]
                                || aux[1] == aux[2] || aux[1] == aux[3] || aux[1] == aux[4] || aux[1] == aux[5] || aux[1] == aux[6] || aux[1] == aux[7] || aux[1] == aux[8] || aux[1] == aux[9] || aux[1] == aux[10] || aux[1] == aux[11] || aux[1] == aux[12] || aux[1] == aux[13]
                                || aux[2] == aux[3] || aux[2] == aux[4] || aux[2] == aux[5] || aux[2] == aux[6] || aux[2] == aux[7] || aux[2] == aux[8] || aux[2] == aux[9] || aux[2] == aux[10] || aux[2] == aux[11] || aux[2] == aux[12] || aux[2] == aux[13]
                                || aux[3] == aux[4] || aux[3] == aux[5] || aux[3] == aux[6] || aux[3] == aux[7] || aux[3] == aux[8] || aux[3] == aux[9] || aux[3] == aux[10] || aux[3] == aux[11] || aux[3] == aux[12] || aux[3] == aux[13]
                                || aux[4] == aux[5] || aux[4] == aux[6] || aux[4] == aux[7] || aux[4] == aux[8] || aux[4] == aux[9] || aux[4] == aux[10] || aux[4] == aux[11] || aux[4] == aux[12] || aux[4] == aux[13]
                                || aux[5] == aux[6] || aux[5] == aux[7] || aux[5] == aux[8] || aux[5] == aux[9] || aux[5] == aux[10] || aux[5] == aux[11] || aux[5] == aux[12] || aux[5] == aux[13]
                                || aux[6] == aux[7] || aux[6] == aux[8] || aux[6] == aux[9] || aux[6] == aux[10] || aux[6] == aux[11] || aux[6] == aux[12] || aux[6] == aux[13]
                                || aux[7] == aux[8] || aux[7] == aux[9] || aux[7] == aux[10] || aux[7] == aux[11] || aux[7] == aux[12] || aux[7] == aux[13]
                                || aux[8] == aux[9] || aux[8] == aux[10] || aux[8] == aux[11] || aux[8] == aux[12] || aux[8] == aux[13]
                                || aux[9] == aux[10] || aux[9] == aux[11] || aux[9] == aux[12] || aux[9] == aux[13]
                                || aux[10] == aux[11] || aux[10] == aux[12] || aux[10] == aux[13]
                                || aux[11] == aux[12] || aux[11] == aux[13]
                                || aux[12] == aux[13]
                    if (hasDuplicates) {
                        aux.removeFirst()
                    } else {
                        return count
                    }
                }
            }
            .let { count }
        return count
    }

    "y2022/data/day06".readFileAsLines().let { input ->
        println("part1: ${part1(input.first())}")
        println("part2: ${part2(input.first())}")
    }
}
