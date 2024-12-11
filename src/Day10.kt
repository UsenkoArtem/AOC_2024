fun main() {
    fun part1(input: List<String>): Long {
        val map = input.map { it.map { if (it == '.') -1 else it.digitToInt() } }
        val rows = map.size
        val cols = map[0].size

        val directions = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))

        fun isValid(x: Int, y: Int, currentHeight: Int): Boolean {
            return x in 0 until rows && y in 0 until cols && map[x][y] == currentHeight + 1
        }

        fun bfsTrailhead(x: Int, y: Int): Int {
            val visited = mutableSetOf<Pair<Int, Int>>()
            val queue = ArrayDeque<Pair<Int, Int>>()
            val reachableNines = mutableSetOf<Pair<Int, Int>>()

            queue.add(Pair(x, y))
            visited.add(Pair(x, y))

            while (queue.isNotEmpty()) {
                val (cx, cy) = queue.removeFirst()
                val currentHeight = map[cx][cy]

                for ((dx, dy) in directions) {
                    val nx = cx + dx
                    val ny = cy + dy

                    if (isValid(nx, ny, currentHeight) && Pair(nx, ny) !in visited) {
                        queue.add(Pair(nx, ny))
                        visited.add(Pair(nx, ny))

                        if (map[nx][ny] == 9) {
                            reachableNines.add(Pair(nx, ny))
                        }
                    }
                }
            }

            return reachableNines.size
        }

        var totalScore = 0
        for (x in 0 until rows) {
            for (y in 0 until cols) {
                if (map[x][y] == 0) {
                    totalScore += bfsTrailhead(x, y)
                }
            }
        }
        return totalScore.toLong()
    }

    fun part2(input: List<String>): Long {
        val map = input.map { it.map { if (it == '.') -1 else it.digitToInt() } }
        val rows = map.size
        val cols = map[0].size

        val directions = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))

        fun isValid(x: Int, y: Int, currentHeight: Int): Boolean {
            return x in 0 until rows && y in 0 until cols && map[x][y] == currentHeight + 1
        }

        fun bfsTrailhead(x: Int, y: Int): Int {
            val visited = mutableSetOf<List<Pair<Int, Int>>>()
            val queue = ArrayDeque<List<Pair<Int, Int>>>()
            val reachableNines = mutableSetOf<List<Pair<Int, Int>>>()

            queue.add(listOf(Pair(x, y)))
            visited.add(listOf(Pair(x, y)))

            while (queue.isNotEmpty()) {
                val curPath = queue.removeFirst()
                val (cx, cy) = curPath.last()
                val currentHeight = map[cx][cy]

                for ((dx, dy) in directions) {
                    val nx = cx + dx
                    val ny = cy + dy
                    val newPath = curPath + Pair(nx, ny)
                    if (isValid(nx, ny, currentHeight) && newPath !in visited) {
                        queue.add(newPath)
                        visited.add(newPath)

                        if (map[nx][ny] == 9) {
                            reachableNines.add(curPath + Pair(nx, ny))
                        }
                    }
                }
            }

            return reachableNines.size
        }

        var totalScore = 0
        for (x in 0 until rows) {
            for (y in 0 until cols) {
                if (map[x][y] == 0) {
                    totalScore += bfsTrailhead(x, y)
                }
            }
        }
        return totalScore.toLong()
    }


    val input = readInput("Day10")
    val input2 = readInput("Day10")
    part1(input).println()
    part2(input2).println()


}
