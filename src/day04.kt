fun main() {
    fun getElves(line: String): Pair<IntRange, IntRange> {
        val (first, second) = line.split(",")
        val (firstBegin, firstEnd) = first.split("-").map(String::toInt)
        val (secondBegin, secondEnd) = second.split("-").map(String::toInt)

        return (firstBegin..firstEnd) to (secondBegin..secondEnd)
    }

    fun part1(input: List<String>): Int = input
        .map { getElves(it) }
        .map { (elf1, elf2) ->
            (elf1.first <= elf2.first && elf1.last >= elf2.last
                    || elf2.first <= elf1.first && elf2.last >= elf1.last)
        }
        .count { it }

    fun part2(input: List<String>): Int = input
        .map { getElves(it) }
        .map { (elf1, elf2) ->
            (elf1.first <= elf2.first && elf1.last >= elf2.last
                    || elf2.first <= elf1.first && elf2.last >= elf1.last
                    || elf1.first == elf2.last
                    || elf1.last == elf2.first
                    || elf1.first <= elf2.first && elf2.first <= elf1.last
                    || elf2.first <= elf1.last && elf1.first <= elf2.last)
        }
        .count { it }

    "day04".readFileAsLines().let { input ->
        println("part1: ${part1(input)}")
        println("part2: ${part2(input)}")
    }
}
