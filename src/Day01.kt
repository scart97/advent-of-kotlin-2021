fun main() {
    fun part1(input: List<String>): Int {
        val depths = input.map { it.toLong()}
        val differences = depths.zipWithNext { a, b -> b - a}
        return differences.count { it > 0 }
    }

    fun part2(input: List<String>): Int {
        val depths = input.map { it.toLong()}
        val windows = depths.windowed(3)
        val windowDepths = windows.map { it.sum() }
        val differences = windowDepths.zipWithNext { a, b -> b - a}
        return differences.count { it > 0 }
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
