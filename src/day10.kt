import kotlin.text.StringBuilder

fun main() {

    fun part1(input: List<String>): Int {
        val cycleOfInterest = listOf(20, 60, 100, 140, 180, 220)
        var cycle = 0
        var signal = 1
        val collectedSignals = mutableListOf<Int>()

        fun computeCycle() {
            cycle++
            if (cycle in cycleOfInterest) {
                collectedSignals.add(signal * cycle)
            }
        }

        input.forEach { line ->
            val command = if (line.length > 4) line.substringBefore(" ") else "noop"
            val amount = if (line.length > 4) line.substringAfter(" ").toInt() else 0

            if (command == "noop") {
                computeCycle()
            }
            if (command == "addx") {
                computeCycle()
                computeCycle()
                signal += amount
            }

        }
        return collectedSignals.sumOf { it }
    }

    fun part2(input: List<String>): Int {
        val cycleOfInterest = listOf(40, 80, 120, 160, 200, 240)
        var cycle = 0

        var drawingLine = StringBuilder()
        var spritePosition = 1

        fun getDrawingChar(): String =
            if (drawingLine.length - 1 == spritePosition || drawingLine.length == spritePosition || drawingLine.length + 1 == spritePosition) "#" else "."

        fun computeCycle() {
            drawingLine.append(getDrawingChar())
            cycle++
            if (cycle in cycleOfInterest) {
                println(drawingLine)
                drawingLine = StringBuilder()
            }
        }

        input.forEach { line ->
            val command = if (line.length > 4) line.substringBefore(" ") else "noop"
            val amount = if (line.length > 4) line.substringAfter(" ").toInt() else 0

            if (command == "noop") {
                computeCycle()
            }
            if (command == "addx") {
                computeCycle()
                computeCycle()

                spritePosition += amount
            }
        }
        return 1
    }

    "day10_test".readFileAsLines().let {
        check(part1(it) == 13140)
        check(part2(it) == 1)
    }

    "day10".readFileAsLines().let { input ->
        println("part1: ${part1(input)}")
        println("part2: ${part2(input)}")
    }
}
