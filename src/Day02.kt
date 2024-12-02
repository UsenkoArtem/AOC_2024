import kotlin.math.abs


private fun isSafeLevel(line: List<Int>): Boolean {
    var isRuleSafe = true
    var isIncrease = true

    if (line[1] < line[0]) {
        isIncrease = false
    }

    for (i in 1 until line.size) {
        if (abs(line[i] - line[i - 1]) > 3 || line[i] == line[i - 1]) {
            isRuleSafe = false
            break
        }
        if (isIncrease) {
            if (line[i] < line[i - 1]) {
                isRuleSafe = false
                break
            }
        } else if (!isIncrease) {

            if (line[i] > line[i - 1]) {
                isRuleSafe = false
                break
            }
        }
    }
    return isRuleSafe
}

fun main() {
    fun part1(input: List<String>): Int {
        val lines = input.map { it.split(" ").map { it.toInt() } }
        var rulesSafe = 0
        for (line in lines) {
            if (isSafeLevel(line)) {
                rulesSafe++
            }
        }
        return rulesSafe

    }

    fun part2(input: List<String>): Int {
        val lines = input.map { it.split(" ").map { it.toInt() } }
        var rulesSafe = 0
        for (line in lines) {
            if (isSafeLevel(line)) {
                rulesSafe++
                continue
            }
            for (i in 0 until line.size) {
                val newLine = line.take(i) + line.takeLast(line.size - i - 1)

                if (isSafeLevel(newLine)) {
                    rulesSafe++
                    break
                }
            }
        }
        return rulesSafe
    }

    val input = readInput("Day02")
    val input2 = readInput("Day02")
    part1(input).println()
    part2(input2).println()
}
