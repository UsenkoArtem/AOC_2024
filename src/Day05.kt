private fun isCorrectPage(page: List<Int>, rules: List<Pair<Int, Int>>): Boolean {
    val numberBefore = mutableListOf<Int>()
    val numberAfter = page.toMutableList()
    for (number in page) {

        val numberMustBeBefore = rules.filter { it.second == number }.map { it.first }
        val numberMustBeAfter = rules.filter { it.first == number }.map { it.second }

        if (numberAfter.any { it in numberMustBeBefore } || numberBefore.any { it in numberMustBeAfter }) {
            return false
        }

        numberBefore.add(number)
        numberAfter.removeFirst()

    }
    return true
}

private fun makeCorrect(page: List<Int>, rules: List<Pair<Int, Int>>): List<Int> {

    val rulesMap = rules.groupBy({ it.first }, { it.second })

    val corrected = page.sortedWith { a, b ->
        if (rulesMap[a].orEmpty().contains(b)) {
            -1
        } else if (rulesMap[b].orEmpty().contains(a)) {
            1
        } else {
            0
        }
    }
    return corrected
}


fun main() {

    fun part1(input: List<String>): Int {
        val rules = input.takeWhile { it.isNotEmpty() }.map {
            it.substringBefore("|").toInt() to it.substringAfter("|").toInt()
        }


        val pages = input.dropWhile { it.isNotEmpty() }.drop(1).map {
            it.split(",").map { it.toInt() }
        }

        val correctPagesNumber = mutableListOf<Int>()

        var index = 0
        for (page in pages) {
            if (isCorrectPage(page, rules)) {
                correctPagesNumber.add(index)
            }
            index++
        }
        var res = 0
        pages.forEachIndexed { index, page ->
            if (index in correctPagesNumber) {
                println("$index: $page ${page[page.size / 2]}")
                res += page[page.size / 2]

            }
        }

        return res
    }

    fun part2(input: List<String>): Int {
        val rules = input.takeWhile { it.isNotEmpty() }.map {
            it.substringBefore("|").toInt() to it.substringAfter("|").toInt()
        }


        val pages = input.dropWhile { it.isNotEmpty() }.drop(1).map {
            it.split(",").map { it.toInt() }
        }

        val correctPages = mutableListOf<List<Int>>()

        var res = 0
        for (page in pages) {
            if (!isCorrectPage(page, rules)) {
                println(page)
                val correctPage = makeCorrect(page, rules)
                correctPages.add(correctPage)
                res += correctPage[correctPage.size / 2]
            }

        }

        correctPages.forEachIndexed { index, page ->
            println("$index: $page ${page[page.size / 2]}")
        }

        return res
    }

    val input = readInput("Day05")
    val input2 = readInput("Day05")
    part1(input).println()
    part2(input2).println()
}
