data class HTPosition(
    var headHorizontal: Int,
    var headVertical: Int,
    var tailHorizontal: Int,
    var tailVertical: Int,
) {
    private val visitedPosition = hashMapOf<String, Int>()
    fun getTailVisitedCount() = visitedPosition.size

    private var previousHeadHorizontal = 0
    private var previousHeadVertical = 0

    fun move(direction: String, count: Int) {
        repeat(count) {
            previousHeadHorizontal = headHorizontal
            previousHeadVertical = headVertical

            println("H before: $headHorizontal,$headVertical")
            println("T before: $tailHorizontal,$tailVertical")

            when (direction) {
                "U" -> {
                    println("=> H -> UP")
                    headVertical++
                }

                "D" -> {
                    println("=> H -> DOWN")
                    headVertical--
                }

                "L" -> {
                    println("=> H -> LEFT")
                    headHorizontal--
                }

                else /*RIGHT*/ -> {
                    println("=> H -> RIGHT")
                    headHorizontal++
                }
            }

            tailFollow()
            println("H after: $headHorizontal,$headVertical")
            println("T after: $tailHorizontal,$tailVertical")

            println("Visited position count: ${visitedPosition.size}")
//            visitedPosition.forEach {
//                println("visited position ${it.key} count ${it.value}")
//            }
            println("-------")
        }
    }

    private fun tailFollow() {
        val horizontalDistance = headHorizontal - tailHorizontal
        val verticalDistance = headVertical - tailVertical

        println("Horizontal distance: $horizontalDistance")
        println("Vertical distance: $verticalDistance")

        if (
            horizontalDistance == -2 && verticalDistance == 1 ||
            horizontalDistance == -2 && verticalDistance == -1 ||
            horizontalDistance == -1 && verticalDistance == -2 ||
            horizontalDistance == 1 && verticalDistance == -2 ||
            horizontalDistance == 1 && verticalDistance == 2 ||
            horizontalDistance == -1 && verticalDistance == 2 ||
            horizontalDistance == 2 && verticalDistance == 1 ||
            horizontalDistance == 2 && verticalDistance == -1
        ) { // move diagonal
            println("=> T -> diagonal")
            tailHorizontal = previousHeadHorizontal
            tailVertical = previousHeadVertical
        } else if (horizontalDistance == 2 && verticalDistance == 0) { // MOVE RIGHT
            println("=> T -> RIGHT")
            tailHorizontal++
        } else if (horizontalDistance == 0 && verticalDistance == 2) {// move up
            println("=> T -> UP")
            tailVertical++
        } else if (horizontalDistance == -2 && verticalDistance == 0) { // move left
            println("=> T -> LEFT")
            tailHorizontal--
        } else if (horizontalDistance == 0 && verticalDistance == -2) { // move down
            println("=> T -> DOWN")
            tailVertical--
        } else {
            println("==> T -> NO movement")
        }

        val horizontalDistanceAfter = headHorizontal - tailHorizontal
        val verticalDistanceAfter = headVertical - tailVertical

        println("H distance after: $horizontalDistanceAfter")
        println("V distance after: $verticalDistanceAfter")


        val position = "$tailHorizontal,$tailVertical"
        val current = visitedPosition[position]
        if (current == null) {
            visitedPosition[position] = 1
        } else {
            visitedPosition[position] = current + 1
        }
    }
}

fun main() {
    val headTailPosition = HTPosition(0, 0, 0, 0)

    fun part1(input: List<String>): Int {
        input.forEach { command ->
            val (direction, count) = command.split(" ")
            headTailPosition.move(direction, count.toInt())
        }
        return headTailPosition.getTailVisitedCount()
    }

    fun part2(input: List<String>): Int {
        return 2
    }

//    "day09_test".readFileAsLines().let { input ->
//        check(part1(input) == 13)
////        check(part2(input) == 2)
//    }

    "day09".readFileAsLines().let { input ->
        println("\n\n=== Starting actual")
        println("part1: ${part1(input)}")
//        println("part2: ${part2(input)}")
    }
}
