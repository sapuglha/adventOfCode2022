fun main() {
    val fileName = "day02"

    fun part1(input: List<String>): Int {
        var sumPoints = 0

        input.forEach { line ->
            val splitList = line.split(" ")
            val hisHand = splitList.first()
            val myHand = splitList.last()

            when (hisHand) {
                "A" /*rock*/ -> when (myHand) {
                    "Y" /*paper - score 2*/-> {
                        // win - score 6
                        sumPoints += 8
                    }
                    "X" /*rock - score 1*/ -> {
                        // draw - score 3
                        sumPoints += 4
                    }
                    "Z" /*scissor - score 3*/ -> {
                        // loss - score 0
                        sumPoints += 3
                    }
                }

                "B" /*paper*/ -> when (myHand) {
                    "Y" /*paper - score 2*/-> {
                        // draw - score 3
                        sumPoints += 5
                    }
                    "X" /*rock - score 1*/ -> {
                        // loss - score 0
                        sumPoints += 1
                    }
                    "Z" /*scissor - score 3*/ -> {
                        // win - score 6
                        sumPoints += 9
                    }
                }

                "C" /*scissors*/ -> when (myHand) {
                    "Y" /*paper - score 2*/-> {
                        // loss - score 0
                        sumPoints += 2
                    }
                    "X" /*rock - score 1*/ -> {
                        // win - score 6
                        sumPoints += 7
                    }
                    "Z" /*scissor - score 3*/ -> {
                        // tie - score 3
                        sumPoints += 6
                    }
                }
            }

        }
        return sumPoints
    }

    fun part2(input: List<String>): Int {
        var sumPoints = 0

        input.forEach { line ->
            val splitList = line.split(" ")
            val hisHand = splitList.first()
            val myHand = splitList.last()

            when (hisHand) {
                "A" /*rock*/ -> when (myHand) {
                    "Y" /* draw- score 3 */ -> {
                        //  rock - score 1
                        sumPoints += 4
                    }
                    "X" /* lose - score 0 */ -> {
                        //  scissor - score 3
                        sumPoints += 3
                    }
                    "Z" /* win - score 6 */ -> {
                        //  paper - score 2
                        sumPoints += 8
                    }
                }

                "B" /*paper*/ -> when (myHand) {
                    "Y" /* draw- score 3 */ -> {
                        // paper - score 2
                        sumPoints += 5
                    }
                    "X" /* lose - score 0 */ -> {
                        // rock - score 1
                        sumPoints += 1
                    }
                    "Z" /* win - score 6 */ -> {
                        // scissor - score 3
                        sumPoints += 9
                    }
                }

                "C" /*scissors*/ -> when (myHand) {
                    "Y" /* draw- score 3 */ -> {
                        // scissor - score 3
                        sumPoints += 6
                    }
                    "X" /* lose - score 0 */ -> {
                        // paper - score 2
                        sumPoints += 2
                    }
                    "Z" /* win - score 6 */ -> {
                        // rock - score 1
                        sumPoints += 7
                    }
                }
            }

        }
        return sumPoints
    }

    val input = fileName.readFileAsLines()

    println("part1: ${part1(input)}")
    println("part2: ${part2(input)}")
}
