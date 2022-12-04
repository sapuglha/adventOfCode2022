import java.lang.IllegalArgumentException

fun main() {
    fun part1(input: List<String>): Int {
        var fullyContained: Int = 0
        input.forEach { line ->
            val (first, second) = line.split(",")
            val (firstBegin, firstEnd) = first.split("-")
            val (secondBegin, secondEnd) = second.split("-")
            val result = (firstBegin.toInt() <= secondBegin.toInt() && firstEnd.toInt() >= secondEnd.toInt() ||
                    secondBegin.toInt() <= firstBegin.toInt() && secondEnd.toInt() >= firstEnd.toInt())

            if (result) fullyContained++
        }
        return fullyContained
    }

    fun part2(input: List<String>): Int {
        var fullyContained: Int = 0
        input.forEach { line ->
            val (first, second) = line.split(",")
            val (firstBegin, firstEnd) = first.split("-")
            val (secondBegin, secondEnd) = second.split("-")
            val result = (firstBegin.toInt() <= secondBegin.toInt() && firstEnd.toInt() >= secondEnd.toInt() ||
                    secondBegin.toInt() <= firstBegin.toInt() && secondEnd.toInt() >= firstEnd.toInt()) ||
                    firstBegin.toInt() == secondBegin.toInt() ||
                    firstBegin.toInt() == secondEnd.toInt() ||
                    firstEnd.toInt() == secondBegin.toInt() ||
                    firstEnd.toInt() == secondEnd.toInt() ||
                    firstBegin.toInt() <= secondBegin.toInt() && secondBegin.toInt() <= firstEnd.toInt() ||
                    secondBegin.toInt() <= firstEnd.toInt() && firstBegin.toInt() <= secondEnd.toInt()


            if (result) fullyContained++
        }
        return fullyContained
    }


    "day04".readFileAsLines().let { input ->
        println("part1: ${part1(input)}")
        println("part2: ${part2(input)}")
    }
}
