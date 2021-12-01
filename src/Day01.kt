fun List<Long>.countPositiveDifferences(): Int {
    val differences = this.zipWithNext { a, b -> b - a }
    return differences.count { it > 0 }
}

fun main() {
    fun part1(input: List<String>): Int {
        val depths = input.map { it.toLong() }
        return depths.countPositiveDifferences()
    }

    fun part2(input: List<String>): Int {
        val depths = input.map { it.toLong() }
        val windowDepths = depths.windowed(3).map { it.sum() }
        return windowDepths.countPositiveDifferences()
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
