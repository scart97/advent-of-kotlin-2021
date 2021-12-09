fun main() {

    fun validIndex(map: List<List<Int>>, index: Pair<Int, Int>): Boolean {
        return (index.first in map.indices) and (index.second in map[0].indices)
    }

    fun checkLocalMinimum(heightMap: List<List<Int>>, index: Pair<Int, Int>): Boolean {
        val (i, j) = index

        val h1 = if (validIndex(heightMap, i to j - 1)) heightMap[i][j - 1] else 10
        val h2 = if (validIndex(heightMap, i to j + 1)) heightMap[i][j + 1] else 10
        val h3 = if (validIndex(heightMap, i + 1 to j)) heightMap[i + 1][j] else 10
        val h4 = if (validIndex(heightMap, i - 1 to j)) heightMap[i - 1][j] else 10

        val current = heightMap[i][j]
        return (current < h1) and (current < h2) and (current < h3) and (current < h4)
    }

    fun part1(input: List<String>): Int {
        val heightMap = input.map { it.map { c -> c.digitToInt() } }
        var riskLevel = 0

        for (i in heightMap.indices) {
            for (j in heightMap[i].indices) {
                if (checkLocalMinimum(heightMap, Pair(i, j))) {
                    riskLevel += heightMap[i][j] + 1
                }
            }
        }

        return riskLevel
    }


    fun expandBasin(basinMap: Array<Array<Int>>, heightMap: List<List<Int>>, index: Pair<Int, Int>, value: Int) {
        if (validIndex(heightMap, index)) {
            if ((basinMap[index.first][index.second] == 0)
                and (heightMap[index.first][index.second] != 9)
            ) {
                basinMap[index.first][index.second] = value

                expandBasin(basinMap, heightMap, index.copy(first = index.first + 1), value)
                expandBasin(basinMap, heightMap, index.copy(first = index.first - 1), value)
                expandBasin(basinMap, heightMap, index.copy(second = index.second + 1), value)
                expandBasin(basinMap, heightMap, index.copy(second = index.second - 1), value)
            }
        }
    }

    fun part2(input: List<String>): Int {
        val heightMap = input.map { it.map { c -> c.digitToInt() } }
        val basinMap = Array(heightMap.size) { Array(heightMap[0].size) { 0 } }
        var numberOfBasins = 0

        for (i in heightMap.indices) {
            for (j in heightMap[i].indices) {
                if (checkLocalMinimum(heightMap, Pair(i, j))) {
                    numberOfBasins += 1
                    expandBasin(basinMap, heightMap, Pair(i, j), numberOfBasins)
                }
            }
        }

        val valueCounts = basinMap.flatten().filter { it != 0 }.groupBy { it }.map { it.value.count() }
        val (x1, x2, x3) = valueCounts.sortedDescending().take(3)

        return x1 * x2 * x3
    }

    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
