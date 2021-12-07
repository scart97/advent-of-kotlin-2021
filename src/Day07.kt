import kotlin.math.abs

fun List<Int>.median(): Int {
    return if (this.size % 2 == 0) {
        this[this.size / 2]
    } else {
        val midpoint = this.size / 2
        (this[midpoint] + this[midpoint + 1]) / 2
    }
}


fun main() {
    fun part1(input: List<String>): Int {
        val crabPositions = input.first().split(',').map { it.toInt() }
        // https://math.stackexchange.com/questions/113270/the-median-minimizes-the-sum-of-absolute-deviations-the-ell-1-norm
        val median = crabPositions.median()
        return crabPositions.sumOf { abs(it - median) }
    }

    fun part2(input: List<String>): Int {
        val crabPositions = input.first().split(',').map { it.toInt() }.sorted()
        val possibleDistances = crabPositions.first().until(crabPositions.last())

        fun customDistance(a: Int, b: Int): Int {
            val n = abs(a - b)
            // sum of first N integers
            return (n * (n + 1)) / 2
        }

        // Find position that minimizes the distance to all crab positions
        return possibleDistances.minOf { d ->
            crabPositions.sumOf { customDistance(d, it) }
        }
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
