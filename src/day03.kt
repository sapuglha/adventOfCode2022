import java.lang.IllegalArgumentException

fun main() {
    fun part1(input: List<String>): Int {
        val list = mutableListOf<Char>()
        input.forEach { line ->
            val (first, second) = line.chunked(line.length / 2)
            first.asIterable()
                .distinct()
                .forEach {
                    if (second.contains(it)) {
                        println(first)
                        println(second)
                        println(it)
                        println()
                        list.add(it)
                    }
                }
        }
        return list
            .sortedDescending()
            .also { println("$it\n") }
            .map { it - 'a' + 1 }
            .map { if (it < 0) it + 58 else it }
            .also { println("$it\n") }
            .sumOf { it }
    }

    fun part2(input: List<String>): Int {
        val list = mutableListOf<Char>()

        val occurrencesMap = mutableMapOf<Char, Int>()
        var final = 0
        return input
            .chunked(3)
            .map { group ->
                val line1 = group[0]
                val line2 = group[1]
                val line3 = group[2]

                var character: String

                var mutableResponse: Char = '_'

                line1.forEach {
                    if (line2.contains(it) && line3.contains(it)) {
                        mutableResponse = it
                    }
                }
                mutableResponse
            }
            .also {
                println(it)
            }
            .map { entry ->
                val value = entry - 'a' + 1
                val value2 = if (value < 0) value + 58 else value
                value2
            }
            .sumOf {
                it
            }
            .also {
                println(it)
            }
    }


    "day03".readFileAsLines().let { input ->
        println("part1: ${part1(input)}")
        println("part2: ${part2(input)}")
    }
}
