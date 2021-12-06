fun main() {
    fun part1(input: List<String>): Int {
        val daysUntilNewFish = input.first().split(',').map { it.toInt() }.toMutableList()
        // Generic solution, just follow the problem rules
        repeat(80) {
            val numberOfNewFish = daysUntilNewFish.count { it == 0 }
            daysUntilNewFish.replaceAll { it - 1 }
            daysUntilNewFish.replaceAll { if (it == -1) 6 else it }
            daysUntilNewFish.addAll(Array(numberOfNewFish) { 8 })
        }

        return daysUntilNewFish.size
    }

    fun part2(input: List<String>): Long {
        val daysUntilNewFish = input.first().split(',').map { it.toInt() }
        // Now only count the number of fish each day
        var fishCounts = MutableList<Long>(9) { 0 }

        for (d in daysUntilNewFish) {
            fishCounts[d] += 1L
        }

        repeat(256) {
            val numberOfNewFish = fishCounts[0]
            fishCounts = fishCounts.drop(1).toMutableList()
            fishCounts.add(8, numberOfNewFish) // New fish
            fishCounts[6] += numberOfNewFish // Existing fish after a cycle
        }

        return fishCounts.sum()
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26984457539L)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
