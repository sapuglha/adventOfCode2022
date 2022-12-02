import java.lang.IllegalArgumentException

fun main() {
    fun part1(input: List<String>): Int = input.sumOf { line ->
        val round = line.split(" ")
        val theirMove = round.first().toHand()
        val myMove = round.last().toHand()

        when (theirMove) {
            Hand.ROCK -> when (myMove) {
                Hand.ROCK -> Outcome.DRAW.score + myMove.score
                Hand.PAPER -> Outcome.WIN.score + myMove.score
                Hand.SCISSOR -> Outcome.LOSE.score + myMove.score
            }

            Hand.PAPER -> when (myMove) {
                Hand.ROCK -> Outcome.LOSE.score + myMove.score
                Hand.PAPER -> Outcome.DRAW.score + myMove.score
                Hand.SCISSOR -> Outcome.WIN.score + myMove.score
            }

            Hand.SCISSOR -> when (myMove) {
                Hand.ROCK -> Outcome.WIN.score + myMove.score
                Hand.PAPER -> Outcome.LOSE.score + myMove.score
                Hand.SCISSOR -> Outcome.DRAW.score + myMove.score
            }
        }
    }

    fun part2(input: List<String>): Int = input.sumOf { line ->
        val round = line.split(" ")
        val theirMove = round.first().toHand()
        val desiredOutcome = round.last().toOutcome()

        when (theirMove) {
            Hand.ROCK -> when (desiredOutcome) {
                Outcome.WIN -> desiredOutcome.score + Hand.PAPER.score
                Outcome.DRAW -> desiredOutcome.score + Hand.ROCK.score
                Outcome.LOSE -> desiredOutcome.score + Hand.SCISSOR.score
            }

            Hand.PAPER -> when (desiredOutcome) {
                Outcome.WIN -> desiredOutcome.score + Hand.SCISSOR.score
                Outcome.DRAW -> desiredOutcome.score + Hand.PAPER.score
                Outcome.LOSE -> desiredOutcome.score + Hand.ROCK.score
            }

            Hand.SCISSOR -> when (desiredOutcome) {
                Outcome.WIN -> desiredOutcome.score + Hand.ROCK.score
                Outcome.DRAW -> desiredOutcome.score + Hand.SCISSOR.score
                Outcome.LOSE -> desiredOutcome.score + Hand.PAPER.score
            }
        }
    }

    "day02".readFileAsLines().let { input ->
        println("part1: ${part1(input)}")
        println("part2: ${part2(input)}")
    }
}

enum class Hand(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSOR(3),
}

enum class Outcome(val score: Int) {
    WIN(6),
    DRAW(3),
    LOSE(0),
}

fun String.toHand(): Hand = when (this) {
    "A", "X" -> Hand.ROCK
    "B", "Y" -> Hand.PAPER
    "C", "Z" -> Hand.SCISSOR
    else -> throw IllegalArgumentException()
}

fun String.toOutcome(): Outcome = when (this) {
    "X" -> Outcome.LOSE
    "Y" -> Outcome.DRAW
    "Z" -> Outcome.WIN
    else -> throw IllegalArgumentException()
}
