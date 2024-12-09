fun main() {
    fun isLoop(
        input: List<CharArray>,
        x: Int,
        y: Int
    ): Boolean {
        val map = input.map { it.copyOf() }.toMutableList()
        var patrolDirection = 0
        var patroX = x
        var patroY = y
        var iterCount= 0

        while (patroX >= 0 && patroY >= 0 && patroX < input.size && patroY < input[0].size) {
            iterCount++
            if (iterCount > 100000) {
                return true
            }
            map[patroX][patroY] = 'X'

            if (patrolDirection == 0) {
                patroX--
                if (patroX < 0) {
                    break
                }
                if (input[patroX][patroY] == '#') {
                    patrolDirection = (patrolDirection + 1) % 4
                    patroX++
                }
            } else if (patrolDirection == 1) {
                patroY++
                if (patroY >= input[0].size) {
                    break
                }
                if (input[patroX][patroY] == '#') {
                    patrolDirection = (patrolDirection + 1) % 4

                    patroY--
                }
            } else if (patrolDirection == 2) {
                patroX++
                if (patroX >= input.size) {
                    break
                }
                if (input[patroX][patroY] == '#') {
                    patrolDirection = (patrolDirection + 1) % 4
                    patroX--
                }
            } else if (patrolDirection == 3) {
                patroY--
                if (patroY < 0) {
                    break
                }
                if (input[patroX][patroY] == '#') {
                    patrolDirection = (patrolDirection + 1) % 4
                    patroY++
                }
            }
        }
        return false
    }

    fun part1(input: List<String>): Int {
        var patroX = 0
        var patroY = 0

        val map = input.map { it.toCharArray() }

        input.forEachIndexed { i, x ->
            x.forEachIndexed { j, y ->
                if (y == '^') {
                    patroX = i
                    patroY = j
                    return@forEachIndexed
                }
            }
        }

        var patrolDirection = 0

        while (patroX >= 0 && patroY >= 0 && patroX < input.size && patroY < input[0].length) {
            map[patroX][patroY] = 'X'

            if (patrolDirection == 0) {
                patroX--
                if (patroX < 0) {
                    break
                }
                if (input[patroX][patroY] == '#') {
                    patrolDirection = (patrolDirection + 1) % 4
                    patroX++
                }
            } else if (patrolDirection == 1) {
                patroY++
                if (patroY >= input[0].length) {
                    break
                }
                if (input[patroX][patroY] == '#') {
                    patrolDirection = (patrolDirection + 1) % 4

                    patroY--
                }
            } else if (patrolDirection == 2) {
                patroX++
                if (patroX >= input.size) {
                    break
                }
                if (input[patroX][patroY] == '#') {
                    patrolDirection = (patrolDirection + 1) % 4
                    patroX--
                }
            } else if (patrolDirection == 3) {
                patroY--
                if (patroY < 0) {
                    break
                }
                if (input[patroX][patroY] == '#') {
                    patrolDirection = (patrolDirection + 1) % 4
                    patroY++
                }
            }
        }


        var res =
            map.sumOf {
                it.sumBy() {
                    if (it == 'X') 1 else 0
                }
            }
        return res
    }

    fun part2(input: List<String>): Int {
        var patroX = 0
        var patroY = 0

        val map = input.map { it.toCharArray() }

        input.forEachIndexed { i, x ->
            x.forEachIndexed { j, y ->
                if (y == '^') {
                    patroX = i
                    patroY = j
                    return@forEachIndexed
                }
            }
        }
        var res = 0
        for (i in 0 until input.size) {
            for (j in 0 until input[0].length) {
                if (i != patroX || j != patroY) {
                    map[i][j] = '#'
                    if (isLoop(map, patroX, patroY)) {
                        res++
                    }
                    map[i][j] = input[i][j]
                }
            }
        }
        return res
    }

    val input = readInput("Day06")
    val input2 = readInput("Day06")
    part1(input).println()
    part2(input2).println()
}
