fun main() {
    val openChunk = arrayOf('(', '[', '{', '<')
    val bracketPair = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')

    fun part1(input: List<String>): Int {
        var highScore = 0

        input.forEach { line ->
            val syntaxParser = ArrayDeque<Char>()

            processLine@ for (c in line) {
                if (c in openChunk) {
                    syntaxParser.add(c)
                } else {
                    val currentOpen = syntaxParser.removeLast()
                    val expected = bracketPair[currentOpen]
                    if (c != expected) {

                        when (c) {
                            ')' -> highScore += 3
                            ']' -> highScore += 57
                            '}' -> highScore += 1197
                            '>' -> highScore += 25137
                            else -> println(c)
                        }
                        break@processLine
                    }
                }
            }
        }

        return highScore
    }


    fun part2(input: List<String>): Long {
        val autocompleteLines = mutableListOf<ArrayDeque<Char>>()

        input.forEach { line ->
            val syntaxParser = ArrayDeque<Char>()
            var corruptedLine = false
            processLine@ for (c in line) {
                if (c in openChunk) {
                    syntaxParser.add(c)
                } else {
                    val currentOpen = syntaxParser.removeLast()
                    val expected = bracketPair[currentOpen]
                    if (c != expected) {
                        corruptedLine = true
                        break@processLine
                    }
                }
            }
            if (!corruptedLine) {
                autocompleteLines.add(syntaxParser)
            }
        }

        val autocompleteScores = autocompleteLines.map {
            var currentScore = 0L
            for (c in it.reversed()) {
                currentScore *= 5
                when (bracketPair[c]) {
                    ')' -> currentScore += 1
                    ']' -> currentScore += 2
                    '}' -> currentScore += 3
                    '>' -> currentScore += 4
                    else -> println(c)
                }
            }
            currentScore
        }
        val midPoint = autocompleteScores.size / 2
        return autocompleteScores.sorted()[midPoint]
    }

    val testInput = readInput("Day10_test")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input)) // 63614124
}
