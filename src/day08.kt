fun main() {
    fun part1(input: List<String>): Int {
        val map = input.map {
            it.toCharArray()
        }

        val rows = input.size
        val columns = map.first().size

        val totalOuter = rows + columns + rows - 2 + columns - 2
        var total = 0

        for (i in 1 until rows - 1) {
            val currentRow = map[i]
            for (j in 1 until columns - 1) {
                val currentItem = currentRow[j]

                var continuation = true

                var checkAbove = true
                var checkBelow = true
                var checkBefore = true
                var checkAfter = true

                // Check above
                var rowIndexAbove = i - 1
                while (rowIndexAbove >= 0 && continuation) {
                    if (map[rowIndexAbove][j] < currentItem)
                        rowIndexAbove--
                    else {
                        continuation = false
                        checkAbove = false
                    }
                }

                continuation = true
                // check below
                var rowIndexBelow = i + 1
                while (rowIndexBelow < rows && continuation) {
                    if (map[rowIndexBelow][j] < currentItem)
                        rowIndexBelow++
                    else {
                        continuation = false
                        checkBelow = false
                    }
                }

                continuation = true
                // check before
                var columnIndexBefore = j - 1
                while (columnIndexBefore >= 0 && continuation) {
                    if (map[i][columnIndexBefore] < currentItem)
                        columnIndexBefore--
                    else {
                        continuation = false
                        checkBefore = false
                    }
                }

                continuation = true
                // check after
                var columnIndexAfter = j + 1
                while (columnIndexAfter < columns && continuation) {
                    if (map[i][columnIndexAfter] < currentItem)
                        columnIndexAfter++
                    else {
                        continuation = false
                        checkAfter = false
                    }
                }


                if (checkAbove || checkBelow || checkBefore || checkAfter) total++
            }
        }
        return totalOuter + total
    }

    fun part2(input: List<String>): Int {
        val map = input.map {
            it.toCharArray()
        }

        val rows = input.size
        val columns = map.first().size
        var highestScore = 0

        val totalOuter = rows + columns + rows - 2 + columns - 2
        var total = 0

        for (i in 1 until rows - 1) {
            val currentRow = map[i]
            for (j in 1 until columns - 1) {
                val currentItem = currentRow[j]

                var continuation = true

                var checkAbove = true
                var checkBelow = true
                var checkBefore = true
                var checkAfter = true

                // Check above
                var rowIndexAbove = i - 1
                var scoreAbove = 0
                while (rowIndexAbove >= 0 && continuation) {
                    scoreAbove++
                    if (map[rowIndexAbove][j] < currentItem) {
                        rowIndexAbove--
                    } else {
                        continuation = false
                        checkAbove = false
                    }
                }

                continuation = true
                // check below
                var rowIndexBelow = i + 1
                var scoreBelow = 0
                while (rowIndexBelow < rows && continuation) {
                    scoreBelow++
                    if (map[rowIndexBelow][j] < currentItem) {
                        rowIndexBelow++
                    } else {
                        continuation = false
                        checkBelow = false
                    }
                }

                continuation = true
                // check before
                var columnIndexBefore = j - 1
                var scoreBefore = 0
                while (columnIndexBefore >= 0 && continuation) {
                    scoreBefore++
                    if (map[i][columnIndexBefore] < currentItem) {
                        columnIndexBefore--
                    } else {
                        continuation = false
                        checkBefore = false
                    }
                }

                continuation = true
                // check after
                var columnIndexAfter = j + 1
                var scoreAfter = 0
                while (columnIndexAfter < columns && continuation) {
                    scoreAfter++
                    if (map[i][columnIndexAfter] < currentItem) {
                        columnIndexAfter++
                    } else {
                        continuation = false
                        checkAfter = false
                    }
                }


                var currentScore = scoreAbove * scoreBelow * scoreAfter * scoreBefore
                if (currentScore > highestScore) highestScore = currentScore
            }
        }
        return highestScore
    }

    "day08_test".readFileAsLines().let { input ->
        check(part1(input) == 21)
        check(part2(input) == 8)
    }

    "day08".readFileAsLines().let { input ->
        println("part1: ${part1(input)}")
        println("part2: ${part2(input)}")
    }
}
