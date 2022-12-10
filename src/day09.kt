data class KnotPosition(
    val name: String,
    var x: Int = 0,
    var y: Int = 0,
)

fun KnotPosition.move(direction: String) = when (direction) {
    "U" -> y++
    "D" -> y--
    "L" -> x--
    else /*RIGHT*/ -> x++
}

private fun KnotPosition.tailFollow(head: KnotPosition, visitedPositions: HashMap<String, Int>) {
    val horizontalDistance = head.x - x
    val verticalDistance = head.y - y

    var moved = true

    /*

    E F 0 1 2
    D - - - 3
    C - I - 4
    B - - - 5
    A 9 8 7 6

    */

    if (horizontalDistance == 0 && verticalDistance == 2) { // 0 - up
        y++
    } else if (horizontalDistance == 1 && verticalDistance == 2 || // 1 diagonal
        horizontalDistance == 2 && verticalDistance == 2 || // 2 diagonal
        horizontalDistance == 2 && verticalDistance == 1 // 3 diagonal
    ) {
        x++
        y++
    } else if (horizontalDistance == 2 && verticalDistance == 0) { // 4 - right
        x++
    } else if (horizontalDistance == 2 && verticalDistance == -1 || // 5 diagonal
        horizontalDistance == 2 && verticalDistance == -2 || // 6 diagonal
        horizontalDistance == 1 && verticalDistance == -2 // 7 diagonal
    ) {
        x++
        y--
    } else if (horizontalDistance == 0 && verticalDistance == -2) { // 8 - down
        y--
    } else if (horizontalDistance == -1 && verticalDistance == -2 || // 9 diagonal
        horizontalDistance == -2 && verticalDistance == -2 || // A diagonal
        horizontalDistance == -2 && verticalDistance == -1 // B diagonal
    ) {
        x--
        y--
    } else if (horizontalDistance == -2 && verticalDistance == 0) { // C - left
        x--
    } else if (horizontalDistance == -2 && verticalDistance == 1 || // D diagonal
        horizontalDistance == -2 && verticalDistance == 2 || // E diagonal
        horizontalDistance == -1 && verticalDistance == 2  // F diagonal
    ) {
        x--
        y++
    } else {
        moved = false
    }

    if (this.name == "T" && moved) {
        val position = "$x,$y"
        val currentPosition = visitedPositions[position]
        if (currentPosition == null) {
            visitedPositions[position] = 1
        } else {
            visitedPositions[position] = currentPosition + 1
        }
    }
}

fun printMap(
    headTailPosition: List<KnotPosition>,
    xStart: Int = 0,
    xEnd: Int = 5,
    yStart: Int = 0,
    yEnd: Int = 5
) {
    val totalX = xEnd - xStart
    for (countX in xStart..xEnd) {
        headTailPosition
            .filter { it.y == totalX - countX }
            .sortedBy { it.x }
            .also { list ->
                for (countY in yStart..yEnd) {
                    val item = list.firstOrNull { it.x == countY }
                    print(item?.name ?: ".")
                }
                println()
            }
    }
    println()
}

fun createRope(size: Int): List<KnotPosition> = mutableListOf<KnotPosition>()
    .apply {
        add(KnotPosition("H"))
        repeat(size - 2) { index ->
            add(KnotPosition("${index + 1}"))
        }
        add(KnotPosition("T"))
    }

fun main() {

    fun part1(input: List<String>): Int {
        val rope = createRope(2)
        val visitedPositions: HashMap<String, Int> = hashMapOf("0,0" to 1)

        input.forEach { command ->
            val (direction, count) = command.split(" ")
            repeat(count.toInt()) {
                rope.first().move(direction)
                rope.last().tailFollow(rope.first(), visitedPositions)
                printMap(rope)
            }
        }
        return visitedPositions.count()
    }

    fun part2(input: List<String>): Int {
        val rope = createRope(10)
        val visitedPositions: HashMap<String, Int> = hashMapOf("0,0" to 1)

        input.forEach { command ->
            val (direction, count) = command.split(" ")
            repeat(count.toInt()) {
                rope.first().move(direction)
                rope
                    .subList(1, rope.size)
                    .forEachIndexed { index, item ->
                        val newIndex = index + 1
                        item.tailFollow(rope[newIndex - 1], visitedPositions)
                    }
                printMap(rope)
//                printMap(headTailPosition, -5, 16, -11, 15)
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
        println("part1: ${part1(input)}")
        println("part2: ${part2(input)}")
    }
}
