fun main() {
    fun part1(input: List<String>): Int {
        val outputWires = input.map {
            it.split(" | ")[1].split(' ')
        }
        val expectedLengths = arrayOf(2, 4, 3, 7)
        return outputWires.flatten().count {
            it.length in expectedLengths
        }
    }

    fun part2(input: List<String>): Int {
        
        fun processOne(line: String): Int {
            val inOutPair = line.split(" | ")
            val inputValues = inOutPair[0].split(' ').sortedBy { c -> c.length }.map { c -> c.toSet() }

            // Those four values have known unique length
            val valueOf1 = inputValues[0]
            val valueOf4 = inputValues[2]
            val valueOf7 = inputValues[1]
            val valueOf8 = inputValues[9]
            // Check the others based on length and number of intersections with known values
            // 2, 3 and 5 have size 5
            // 0, 6 and 9 have size 6
            val valueOf3 = inputValues.first { (it.intersect(valueOf1).size == 2) and (it.size == 5) }
            val valueOf9 = inputValues.first { (it.intersect(valueOf3).size == 5) and (it.size == 6) }
            val valueOf6 = inputValues.first { (it.intersect(valueOf1).size == 1) and (it.size == 6) }
            val valueOf0 =
                inputValues.first {
                    (it != valueOf6) and (it != valueOf9) and (it.size == 6)
                }
            val valueOf5 =
                inputValues.first { (it.intersect(valueOf9).size == 5) and (it != valueOf3) and (it.size == 5) }
            val valueOf2 = inputValues.first { (it != valueOf3) and (it != valueOf5) and (it.size == 5) }

            val decodedWiring =
                arrayOf(
                    valueOf0,
                    valueOf1,
                    valueOf2,
                    valueOf3,
                    valueOf4,
                    valueOf5,
                    valueOf6,
                    valueOf7,
                    valueOf8,
                    valueOf9
                ).map { it.toSortedSet() }

            val outputWires = inOutPair[1].split(' ').map { c -> c.toSortedSet() }

            val decodedNumber = outputWires.map { out ->
                decodedWiring.indexOfFirst { decoded ->
                    decoded == out
                }
            }

            return 1000 * decodedNumber[0] + 100 * decodedNumber[1] + 10 * decodedNumber[2] + decodedNumber[3]
        }

        return input.sumOf { processOne(it) }
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
