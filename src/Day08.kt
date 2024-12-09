fun main() {
    fun part1(input: List<String>): Long {
        val map: MutableMap<Char, MutableList<Pair<Int, Int>>> = mutableMapOf()
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] != '.') {
                    map[input[i][j]] = ((map[input[i][j]] ?: mutableListOf()) + (i to j)).toMutableList()
                    continue
                }
            }
        }

        val result = List(input.size) { MutableList(input[0].length) { '.' } }

        for ((_, location) in map) {
            for (i in location.indices) {
                for (j in location.indices) {
                    if (i == j) continue
                    val delI = location[i].first - location[j].first
                    val delJ = location[i].second - location[j].second
                    val nI = location[i].first + delI
                    val nJ = location[i].second + delJ
                    if (nI in result.indices && nJ in result[0].indices) {
                        result[nI][nJ] = '#'
                    }
                }
            }
        }

        result.forEach { println(it) }

        return result.flatten().count { it == '#' }.toLong()
    }

    fun part2(input: List<String>): Long {
        val map: MutableMap<Char, MutableList<Pair<Int, Int>>> = mutableMapOf()
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] != '.') {
                    map[input[i][j]] = ((map[input[i][j]] ?: mutableListOf()) + (i to j)).toMutableList()
                    continue
                }
            }
        }

        val result = List(input.size) { MutableList(input[0].length) { '.' } }

        for ((_, location) in map) {
            for (i in location.indices) {
                for (j in location.indices) {
                    if (i == j) continue
                    val delI = location[i].first - location[j].first
                    val delJ = location[i].second - location[j].second
                    var count = 0
                    while (true) {
                        val nI = location[i].first + delI * count
                        val nJ = location[i].second + delJ * count
                        if (nI in result.indices && nJ in result[0].indices) {
                            result[nI][nJ] = '#'
                        } else {
                            break
                        }
                        count++
                    }

                }
            }
        }

        result.forEach { println(it) }

        return result.flatten().count { it == '#' }.toLong()
    }


    val input = readInput("Day08")
    val input2 = readInput("Day08")
    part1(input).println()
    part2(input2).println()


}
