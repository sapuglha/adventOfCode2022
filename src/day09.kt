data class KnotPosition(
    val name: String,
    var currentH: Int = 0,
    var currentV: Int = 0,
)

fun KnotPosition.move(direction: String) =
    when (direction) {
        "U" -> {
            currentV++
        }

        "D" -> {
            currentV--
        }

        "L" -> {
            currentH--
        }

        else /*RIGHT*/ -> {
            currentH++
        }
    }


private fun KnotPosition.tailFollow(head: KnotPosition, visitedPositions: HashMap<String, Int>?) {
    val horizontalDistance = head.currentH - currentH
    val verticalDistance = head.currentV - currentV

    var moved = true

    /*

    E F 0 1 2
    D - - - 3
    C - I - 4
    B - - - 5
    A 9 8 7 6

    */

    if (horizontalDistance == 0 && verticalDistance == 2) { // 0 - up
        currentV++
    } else if (horizontalDistance == 1 && verticalDistance == 2) { // 1
        currentV++
        currentH++
    } else if (horizontalDistance == 2 && verticalDistance == 2) { // 2
        currentV++
        currentH++
    } else if (horizontalDistance == 2 && verticalDistance == 1) { // 3
        currentH++
        currentV++
    } else if (horizontalDistance == 2 && verticalDistance == 0) { // 4 - right
        currentH++
    } else if (horizontalDistance == 2 && verticalDistance == -1) { // 5
        currentH++
        currentV--
    } else if (horizontalDistance == 2 && verticalDistance == -2) { // 6
        currentH++
        currentV--
    } else if (horizontalDistance == 1 && verticalDistance == -2) { // 7
        currentH++
        currentV--
    } else if (horizontalDistance == 0 && verticalDistance == -2) { // 8 - down
        currentV--
    } else if (horizontalDistance == -1 && verticalDistance == -2) { // 9
        currentV--
        currentH--
    } else if (horizontalDistance == -2 && verticalDistance == -2) { // A
        currentV--
        currentH--
    } else if (horizontalDistance == -2 && verticalDistance == -1) { // B
        currentH--
        currentV--
    } else if (horizontalDistance == -2 && verticalDistance == 0) { // C - left
        currentH--
    } else if (horizontalDistance == -2 && verticalDistance == 1) { // D
        currentH--
        currentV++
    } else if (horizontalDistance == -2 && verticalDistance == 2) { // E
        currentH--
        currentV++
    } else if (horizontalDistance == -1 && verticalDistance == 2) { // F
        currentH--
        currentV++
    } else {
        moved = false
    }

    if (visitedPositions != null && moved) {
        val position = "$currentH,$currentV"
        val currentPosition = visitedPositions[position]
        if (currentPosition == null) {
            visitedPositions[position] = 1
        } else {
            visitedPositions[position] = currentPosition + 1
        }
    }
}

fun printMap(
    headTailPosition: MutableList<KnotPosition>,
    xStart: Int = 0,
    xEnd: Int = 5,
    yStart: Int = 0,
    yEnd: Int = 5
) {
    val totalX = xEnd - xStart
    for (countX in xStart..xEnd) {
        headTailPosition
            .filter { it.currentV == totalX - countX }
            .sortedBy { it.currentH }
            .also { list ->
                for (countY in yStart..yEnd) {
                    val item = list.firstOrNull { it.currentH == countY }
                    print(item?.name ?: ".")
                }
                println()
            }
    }
    println()
}

fun main() {

    fun part1(input: List<String>): Int {
        val headTailPosition = mutableListOf(KnotPosition("H"), KnotPosition("T"))
        val visitedPositions: HashMap<String, Int> = hashMapOf("0,0" to 1)

        input.forEach { command ->
            println("== $command ==")
            val (direction, count) = command.split(" ")
            repeat(count.toInt()) {
                headTailPosition.first().move(direction)
                headTailPosition.last().tailFollow(headTailPosition.first(), visitedPositions)
                printMap(headTailPosition)
            }
        }
        return visitedPositions.count()
    }

    fun part2(input: List<String>): Int {
        val headTailPosition = mutableListOf<KnotPosition>().apply {
            add(KnotPosition("H"))
            repeat(9) { index ->
                add(KnotPosition("${index + 1}"))
            }
        }

        val visitedPositions: HashMap<String, Int> = hashMapOf("0,0" to 1)

        input.forEach { command ->
            println("== $command ==")
            val (direction, count) = command.split(" ")
            repeat(count.toInt()) {
                headTailPosition.first().move(direction)
                headTailPosition.subList(1, headTailPosition.size).forEachIndexed { index, item ->
                    val newIndex = index + 1
                    println("moving tail $newIndex")
                    if (newIndex == headTailPosition.size - 1) {// last item
                        item.tailFollow(headTailPosition[newIndex - 1], visitedPositions)
                    } else {
                        item.tailFollow(headTailPosition[newIndex - 1], null)
                    }
                }
//                printMap(headTailPosition)
                printMap(headTailPosition, -5, 16, -11, 15)
            }
        }
        return visitedPositions.count()
    }

    "day09_test1".readFileAsLines().let {
        check(part1(it) == 13)
        check(part2(it) == 1)
    }

    check(part2("day09_test2".readFileAsLines()) == 36)

    "day09".readFileAsLines().let { input ->
        println("\n\n=== Starting actual")
        println("part1: ${part1(input)}")
        println("part2: ${part2(input)}")
    }
}
