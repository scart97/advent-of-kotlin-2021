fun main() {
    fun part1(input: List<String>): Long {
        var depth = 0L
        var horizontal = 0L
        val commands = input.map { it.split(' ') }.map { it[0] to it[1].toLong() }

        commands.forEach {
            val (direction, amount) = it
            when (direction) {
                "forward" -> horizontal += amount
                "up" -> depth -= amount
                "down" -> depth += amount
            }
        }

        return depth * horizontal
    }


    fun part2(input: List<String>): Long {
        var depth = 0L
        var horizontal = 0L
        var aim = 0L
        val commands = input.map { it.split(' ') }.map { it[0] to it[1].toLong() }

        commands.forEach {
            val (direction, amount) = it
            when (direction) {
                "forward" -> {
                    horizontal += amount; depth += aim * amount
                }
                "up" -> aim -= amount
                "down" -> aim += amount
            }
        }

        return depth * horizontal
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150L)
    check(part2(testInput) == 900L)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
