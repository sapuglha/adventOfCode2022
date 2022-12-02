fun main() {
    val fileName = "day01"

    fun sumElves(input: List<String>): List<Int> {
        val response: MutableList<Int> = mutableListOf()
        val sublist: MutableList<Int> = mutableListOf()

        input.forEach {
            if (it.isNotBlank()) {
                sublist.add(it.toInt())
            } else {
                response.add(
                    sublist.sumOf { it }
                )
                sublist.clear()
            }
        }
        return response
    }

    fun sumElves(input: String): List<Int> =
        input
            .trim()
            .split("\n\n")
            .map { elf ->
                elf.split("\n")
                    .map { it.toInt() }
                    .sumOf { it }
            }

    fun solutionPart1(input: String): Int =
        sumElves(input)
            .maxOf { it }

    fun solutionPart2(input: String): Int =
        sumElves(input)
            .sortedDescending()
            .take(3)
            .sumOf { it }

    fun solutionPart1(input: List<String>): Int =
        sumElves(input)
            .maxOf { it }

    fun solutionPart2(input: List<String>): Int =
        sumElves(input)
            .sortedDescending()
            .take(3)
            .sumOf { it }

    val inputAsText = fileName.readFileAsText()
    println("part1 from text: ${solutionPart1(inputAsText)}")
    println("part2 from text: ${solutionPart2(inputAsText)}")

    val inputAsLines = fileName.readFileAsLines()
    println("part1 from lines: ${solutionPart1(inputAsLines)}")
    println("part2 from lines: ${solutionPart2(inputAsLines)}")
}
