fun main() {
    fun part1(input: List<String>): Long {
        val correctMul = mutableListOf<Long>()
        var isEnabled = true
        for (inst in input) {
            var i = 0
            while (i < inst.length - 4) {
                if (inst[i] == 'm' && inst[i + 1] == 'u' && inst[i + 2] == 'l') {
                    i += 3
                    if (inst[i] == '(') {
                        i++
                        var number = 0
                        var number2 = 0
                        if (inst[i].isDigit()) {
                            number = inst[i].toString().toInt()
                            i++
                        } else {
                            continue
                        }
                        if (inst[i].isDigit()) {
                            number = number * 10 + inst[i].toString().toInt()
                            i++
                        }
                        if (inst[i].isDigit()) {
                            number = number * 10 + inst[i].toString().toInt()
                            i++
                        }
                        if (inst[i] != ',') {
                            continue
                        }
                        i++
                        if (inst[i].isDigit()) {
                            number2 = inst[i].toString().toInt()
                            i++
                        } else {
                            continue
                        }
                        if (inst[i].isDigit()) {
                            number2 = number2 * 10 + inst[i].toString().toInt()
                            i++
                        }
                        if (inst[i].isDigit()) {
                            number2 = number2 * 10 + inst[i].toString().toInt()
                            i++
                        }

                        if (inst[i] != ')') {
                            continue
                        }
                        i++
                        if (isEnabled)
                            correctMul.add(number.toLong() * number2.toLong())

                    }
                } else {
                    if (inst[i] == 'd' && inst[i + 1] == 'o' && inst[i + 2] == '(' && inst[i + 3] == ')')
                        isEnabled = true
                    else if (inst[i] == 'd' && inst[i + 1] == 'o'
                        && inst[i + 2] == 'n'
                        && inst[i + 3].toString() == "'"
                        && inst[i + 4] == 't'
                        && inst[i + 5] == '('
                        && inst[i + 6] == ')'
                    )
                        isEnabled = false

                    i++
                }
            }
        }
        return correctMul.sum()
    }

    fun part2(input: List<String>): Long {
        val correctMul = mutableListOf<Long>()
        for (inst in input) {
            var i = 0
            while (i < inst.length - 4) {
                if (inst[i] == 'm' && inst[i + 1] == 'u' && inst[i + 2] == 'l') {
                    i += 3
                    if (inst[i] == '(') {
                        i++
                        var number = 0
                        var number2 = 0
                        if (inst[i].isDigit()) {
                            number = inst[i].toString().toInt()
                            i++
                        } else {
                            continue
                        }
                        if (inst[i].isDigit()) {
                            number = number * 10 + inst[i].toString().toInt()
                            i++
                        }
                        if (inst[i].isDigit()) {
                            number = number * 10 + inst[i].toString().toInt()
                            i++
                        }
                        if (inst[i] != ',') {
                            continue
                        }
                        i++
                        if (inst[i].isDigit()) {
                            number2 = inst[i].toString().toInt()
                            i++
                        } else {
                            continue
                        }
                        if (inst[i].isDigit()) {
                            number2 = number2 * 10 + inst[i].toString().toInt()
                            i++
                        }
                        if (inst[i].isDigit()) {
                            number2 = number2 * 10 + inst[i].toString().toInt()
                            i++
                        }

                        if (inst[i] != ')') {
                            continue
                        }
                        i++

                        correctMul.add(number.toLong() * number2.toLong())

                    }
                } else i++
            }
        }
        return correctMul.sum()
    }

    val input = readInput("Day03")
    val input2 = readInput("Day03")
    part2(input2).println()
    part1(input).println()
}
