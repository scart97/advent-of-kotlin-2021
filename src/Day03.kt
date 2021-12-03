fun main() {
    fun part1(input: List<String>): Int {
        var gammaBinary = ""
        var epsilonBinary = ""
        for (i in input[0].indices) {
            val count = input.count { it[i] == '1' }
            if (2 * count > input.size) {
                gammaBinary += "1"
                epsilonBinary += "0"
            } else {
                gammaBinary += "0"
                epsilonBinary += "1"
            }
        }
        val gamma = gammaBinary.toInt(2)
        val epsilon = epsilonBinary.toInt(2)
        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {
        var currentList = input

        for (i in input[0].indices) {
            val count = currentList.count { it[i] == '1' }
            currentList = if (2 * count >= currentList.size) {
                currentList.filter { it[i] == '1' }
            } else {
                currentList.filter { it[i] == '0' }
            }
            if (currentList.size == 1)
                break
        }

        val oxygen = currentList.first().toInt(2)

        currentList = input
        for (i in input[0].indices) {
            val count = currentList.count { it[i] == '1' }
            currentList = if (2 * count < currentList.size) {
                currentList.filter { it[i] == '1' }
            } else {
                currentList.filter { it[i] == '0' }
            }
            if (currentList.size == 1)
                break
        }
        val co2 = currentList.first().toInt(2)

        return oxygen * co2
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
