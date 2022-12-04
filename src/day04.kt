fun main() {
    data class ElfArea(
        val begin: Int,
        val end: Int,
    )

    fun getElves(line: String): Pair<ElfArea, ElfArea> {
        val (first, second) = line.split(",")
        val (firstBegin, firstEnd) = first.split("-").map(String::toInt)
        val (secondBegin, secondEnd) = second.split("-").map(String::toInt)

        return ElfArea(firstBegin, firstEnd) to ElfArea(secondBegin, secondEnd)
    }

    fun part1(input: List<String>): Int = input
        .map { getElves(it) }
        .map { elves ->
            (elves.first.begin <= elves.second.begin && elves.first.end >= elves.second.end
                    || elves.second.begin <= elves.first.begin && elves.second.end >= elves.first.end)
        }
        .count { it }

    fun part2(input: List<String>): Int = input
        .map { getElves(it) }
        .map { elves ->
            (elves.first.begin <= elves.second.begin && elves.first.end >= elves.second.end
                    || elves.second.begin <= elves.first.begin && elves.second.end >= elves.first.end
                    || elves.first.begin == elves.second.end
                    || elves.first.end == elves.second.begin
                    || elves.first.begin <= elves.second.begin && elves.second.begin <= elves.first.end
                    || elves.second.begin <= elves.first.end && elves.first.begin <= elves.second.end)
        }
        .count { it }

    "day04".readFileAsLines().let { input ->
        println("part1: ${part1(input)}")
        println("part2: ${part2(input)}")
    }
}
