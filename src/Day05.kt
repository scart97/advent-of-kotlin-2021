data class LinePair(val start: Pair<Int, Int>, val end: Pair<Int, Int>) {
    fun checkHorizontal() = start.first == end.first
    fun checkVertical() = start.second == end.second

    fun checkVerticalOrHorizontal() = checkVertical() or checkHorizontal()

    fun getXRange(): IntProgression {
        return if (start.first < end.first)
            start.first.rangeTo(end.first)
        else
            start.first.downTo(end.first)
    }

    fun getYRange(): IntProgression {
        return if (start.second < end.second)
            start.second.rangeTo(end.second)
        else
            start.second.downTo(end.second)
    }
}

fun List<String>.toIntPair(): Pair<Int, Int> {
    return Pair(this[0].toInt(), this[1].toInt())
}

fun getLines(input: List<String>): List<LinePair> {
    return input.map {
        val pair = it.trim().split(" -> ")
        val startPoint = pair[0].split(",").toIntPair()
        val endPoint = pair[1].split(",").toIntPair()
        LinePair(startPoint, endPoint)
    }
}

fun populateSimpleLines(grid: MutableList<MutableList<Int>>, lines: List<LinePair>) {
    for (line in lines) {
        // Draw the line points
        if (line.checkHorizontal()) {
            for (y in line.getYRange()) {
                grid[y][line.start.first] += 1
            }
        } else {
            for (x in line.getXRange()) {
                grid[line.start.second][x] += 1
            }
        }
    }
}


fun main() {
    fun part1(input: List<String>): Int {
        val lines = getLines(input).filter { it.checkVerticalOrHorizontal() }

        val grid = MutableList(1000) { MutableList(1000) { 0 } }
        populateSimpleLines(grid, lines)

        return grid.flatten().count { it > 1 }
    }

    fun part2(input: List<String>): Int {
        val simpleLines = getLines(input).filter { it.checkVerticalOrHorizontal() }
        val otherLines = getLines(input).filter { !it.checkVerticalOrHorizontal() }

        val grid = MutableList(1000) { MutableList(1000) { 0 } }
        populateSimpleLines(grid, simpleLines)

        for (line in otherLines) {
            // 45 degree lines have the same amount of x and y elements
            for ((x, y) in line.getXRange().zip(line.getYRange()))
                grid[y][x] += 1
        }

        return grid.flatten().count { it > 1 }
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
