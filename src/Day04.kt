fun main() {
    fun checkBoard(board: List<MutableList<String>>): Boolean {
        val rowWin = board.any {
            it.all { c -> c == "-1" }
        }
        var colWin = false
        for (i in 0..4) {
            colWin = board.all {
                it[i] == "-1"
            } or colWin
        }
        return rowWin or colWin
    }

    fun buildBoards(input: List<String>): List<List<MutableList<String>>> {
        val onlyBoards = input.drop(1).filter { it.isNotEmpty() }
        return onlyBoards.map {
            it.split(' ').filter { c -> c.isNotEmpty() }.toMutableList()
        }.chunked(5)
    }

    fun part1(input: List<String>): Int {
        val numbers = input[0].split(",")
        val boards = buildBoards(input)
        for (number in numbers) {
            for (board in boards) {
                // Update found number in bingo board
                board.forEach { row ->
                    val idx = row.indexOf(number)
                    if (idx != -1) {
                        row[idx] = "-1"
                    }
                }
                // Check solution
                if (checkBoard(board)) {
                    val values = board.flatten().filter { it != "-1" }.map { it.toInt() }
                    return values.sum() * number.toInt()
                }
            }

        }
        return -1
    }

    fun part2(input: List<String>): Int {
        val numbers = input[0].split(",")
        var boards = buildBoards(input)

        for (number in numbers) {
            for (board in boards) {
                // Update found number in bingo board
                board.forEach { row ->
                    val idx = row.indexOf(number)
                    if (idx != -1) {
                        row[idx] = "-1"
                    }
                }
            }
            // Check problem solution
            if ((boards.size == 1) and checkBoard(boards.first())) {
                val values = boards.first().flatten().filter { it != "-1" }.map { it.toInt() }
                return values.sum() * number.toInt()
            }
            // Drop all solved boards before next number is drawn
            boards = boards.filter { !checkBoard(it) }
        }
        return -1
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
