import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        var (left, right) = input.map { it.substringBefore(" ").toInt() to it.substringAfterLast(" ").toInt() }.unzip()
        left = left.sorted()
        right = right.sorted()
        return left.zip(right).sumOf { (l, r) -> abs(l - r) }
    }

    fun part2(input: List<String>): Int {
        var (left, right) = input.map { it.substringBefore(" ").toInt() to it.substringAfterLast(" ").toInt() }.unzip()
        left = left.sorted()
        right = right.sorted()
        var res = 0
        var jStartIndex = 0
        for (i in left.indices) {
            var count = 0
            var fistSimilarIndex = -1
            for (j in jStartIndex until right.size) {
                if (left[i] == right[j]) {
                    count++
                    if (fistSimilarIndex == -1) {
                        fistSimilarIndex = j
                    }
                }
                if (right[j] > left[i]) {
                    break
                }
            }
            if (fistSimilarIndex != -1) {
                jStartIndex = fistSimilarIndex
            }
            res += count * left[i]
        }
        return res
    }

    val input = readInput("Day01")
    val input2 = readInput("Day01")
    part1(input).println()
    part2(input2).println()
}
