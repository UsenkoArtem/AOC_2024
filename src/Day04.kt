fun main() {
    // Read input once
    val input = readInput("Day04")

    // Execute Part 1 and Part 2
    val part1Result = part1(input)
    println("Part 1 Result: $part1Result")

    val part2Result = part2(input)
    println("Part 2 Result: $part2Result")
}

// Part 1: Searching for 'XMAS' and 'SAMX' in all directions
fun part1(input: List<String>): Long {
    val numRows = input.size
    val numCols = if (numRows > 0) input[0].length else 0
    val matrix = Array(numRows) { CharArray(numCols) { ' ' } }
    var count = 0

    // Define the patterns to search
    val patterns = listOf("XMAS", "SAMX")

    // Define directions: right, down, diagonal down-right, diagonal up-right
    val directions = listOf(
        Pair(0, 1),   // Horizontal
        Pair(1, 0),   // Vertical
        Pair(1, 1),   // Diagonal Down-Right
        Pair(-1, 1)   // Diagonal Up-Right
    )

    for (row in 0 until numRows) {
        for (col in 0 until numCols) {
            for (pattern in patterns) {
                for ((dRow, dCol) in directions) {
                    if (matchesPattern(input, row, col, dRow, dCol, pattern)) {
                        markMatrix(matrix, input, row, col, dRow, dCol, pattern)
                        count++
                    }
                }
            }
        }
    }

    // Optionally print the matrix
    // printMatrix(matrix)

    return count.toLong()
}

// Part 2: Searching for specific multi-character patterns
fun part2(input: List<String>): Long {
    val numRows = input.size
    val numCols = if (numRows > 0) input[0].length else 0
    val matrix = Array(numRows) { CharArray(numCols) { '.' } }
    var count = 0

    // Define complex patterns as lists of relative positions and expected characters
    val patterns = listOf(
        listOf(
            Triple(0, 0, 'M'),
            Triple(1, 1, 'A'),
            Triple(2, 2, 'S'),
            Triple(0, 2, 'S'),
            Triple(2, 0, 'M')
        ),
        listOf(
            Triple(0, 0, 'S'),
            Triple(1, 1, 'A'),
            Triple(2, 2, 'M'),
            Triple(0, 2, 'S'),
            Triple(2, 0, 'M')
        ),
        listOf(
            Triple(0, 0, 'M'),
            Triple(1, 1, 'A'),
            Triple(2, 2, 'S'),
            Triple(0, 2, 'M'),
            Triple(2, 0, 'S')
        ),
        listOf(
            Triple(0, 0, 'S'),
            Triple(1, 1, 'A'),
            Triple(2, 2, 'M'),
            Triple(0, 2, 'M'),
            Triple(2, 0, 'S')
        )
    )

    for (row in 0 until numRows) {
        for (col in 0 until numCols) {
            for (pattern in patterns) {
                if (matchesComplexPattern(input, row, col, pattern)) {
                    markComplexMatrix(matrix, input, row, col, pattern)
                    count++
                }
            }
        }
    }

    // Optionally print the matrix
    // printMatrix(matrix)

    return count.toLong()
}

// Helper function to check simple patterns
fun matchesPattern(
    input: List<String>,
    row: Int,
    col: Int,
    dRow: Int,
    dCol: Int,
    pattern: String
): Boolean {
    val numRows = input.size
    val numCols = if (numRows > 0) input[0].length else 0

    for (k in pattern.indices) {
        val newRow = row + dRow * k
        val newCol = col + dCol * k
        if (newRow !in 0 until numRows || newCol !in 0 until numCols || input[newRow][newCol] != pattern[k]) {
            return false
        }
    }
    return true
}

// Helper function to mark the matrix for simple patterns
fun markMatrix(
    matrix: Array<CharArray>,
    input: List<String>,
    row: Int,
    col: Int,
    dRow: Int,
    dCol: Int,
    pattern: String
) {
    for (k in pattern.indices) {
        val newRow = row + dRow * k
        val newCol = col + dCol * k
        matrix[newRow][newCol] = input[newRow][newCol]
    }
}

// Helper function to check complex patterns
fun matchesComplexPattern(
    input: List<String>,
    row: Int,
    col: Int,
    pattern: List<Triple<Int, Int, Char>>
): Boolean {
    val numRows = input.size
    val numCols = if (numRows > 0) input[0].length else 0

    for ((dRow, dCol, expectedChar) in pattern) {
        val newRow = row + dRow
        val newCol = col + dCol
        if (newRow !in 0 until numRows || newCol !in 0 until numCols || input[newRow][newCol] != expectedChar) {
            return false
        }
    }
    return true
}

// Helper function to mark the matrix for complex patterns
fun markComplexMatrix(
    matrix: Array<CharArray>,
    input: List<String>,
    row: Int,
    col: Int,
    pattern: List<Triple<Int, Int, Char>>
) {
    for ((dRow, dCol, _) in pattern) {
        val newRow = row + dRow
        val newCol = col + dCol
        matrix[newRow][newCol] = input[newRow][newCol]
    }
}

// Optional: Function to print the matrix
fun printMatrix(matrix: Array<CharArray>) {
    for (row in matrix) {
        println(row.joinToString(""))
    }
}