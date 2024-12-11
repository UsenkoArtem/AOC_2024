fun main() {
    fun part1(input: List<String>): Long {
        var rocks = input.first().split(" ").map { it.toLong() }
        repeat(75) {


            val newRoks = mutableListOf<Long>()

            for (rock in rocks) {
                if (rock == 0L) {
                    newRoks.add(1)
                } else {
                    if (rock.toString().length % 2 == 0) {
                        val len = rock.toString().length
                        val half = len / 2
                        val first = rock.toString().substring(0, half)
                        val second = rock.toString().substring(half, len).dropWhile { it == '0' }
                        newRoks.add(first.toLong())
                        if (second.isNotEmpty()) newRoks.add(second.toLong())
                        else newRoks.add(0)
                    } else {
                        newRoks.add(rock * 2024)
                    }
                }
            }
            rocks = newRoks
        }
        println(rocks)
        return rocks.size.toLong()
    }

    fun part2(input: List<String>): Long {
        var rocks = input.first().split(" ").map { it.toLong() }
        val prevStateMap = Array<HashMap<Long, Long>>(75) { hashMapOf() }

        fun transformRock(rock: Long): List<Long> {
            if (rock == 0L) return listOf(1)
            if (rock.toString().length % 2 == 0) {
                val len = rock.toString().length
                val half = len / 2
                val first = rock.toString().substring(0, half)
                val second = rock.toString().substring(half, len).dropWhile { it == '0' }
                return listOf(first.toLong()) + if (second.isNotEmpty()) listOf(second.toLong()) else listOf(0)
            } else {
                return listOf(rock * 2024)
            }
        }

        fun generate(rocks: Long, generation: Long): Long {
            if (generation >= 75) return 1
            if (prevStateMap[generation.toInt()].containsKey(rocks.hashCode().toLong())) {
                return prevStateMap[generation.toInt()][rocks.hashCode().toLong()]!!
            }

            val newRocks = transformRock(rocks)
            val result = newRocks.sumOf { generate(it, generation + 1) }
            prevStateMap[generation.toInt()][rocks.hashCode().toLong()] = result
            return result
        }

        var res = 0L
        for (rock in rocks) {
            res += generate(rock, 0)
        }
        return res
    }


    val input = readInput("Day11")
    val input2 = readInput("Day11")
//    part1(input).println()
    part2(input2).println()


}
