fun main() {


    data class Calibration(val expectedNumber: Long, val numbers: List<Long>)


    fun canMakeNumber(expectedNumber: Long, curNumber: Long, numbers: List<Long>): Boolean {
        if (numbers.isEmpty() && curNumber != expectedNumber) {
            return false
        }

        if (curNumber == expectedNumber && numbers.isEmpty()) {
            return true
        }

        if (curNumber > expectedNumber) {
            return false
        }

        val plus = canMakeNumber(expectedNumber, curNumber + numbers.first(), numbers.drop(1))
        val multiply = canMakeNumber(expectedNumber, curNumber * numbers.first(), numbers.drop(1))

        return plus || multiply

    }

    fun canMakeNumberV2(expectedNumber: Long, curNumber: Long, numbers: List<Long>): Boolean {
        if (numbers.isEmpty() && curNumber != expectedNumber) {
            return false
        }

        if (curNumber == expectedNumber && numbers.isEmpty()) {
            return true
        }

        if (curNumber > expectedNumber) {
            return false
        }

        val plus = canMakeNumberV2(expectedNumber, curNumber + numbers.first(), numbers.drop(1))
        val multiply = canMakeNumberV2(expectedNumber, curNumber * numbers.first(), numbers.drop(1))

        var thirdOperatorNumber = curNumber
        val num = numbers.first()


        val thirdOperator =
            canMakeNumberV2(expectedNumber, (curNumber.toString() + num.toString()).toLong(), numbers.drop(1))


        return plus || multiply || thirdOperator
    }

    fun part1(input: List<String>): Long {

        val calibrations = input.map {
            val expectedNumber = it.substringBefore(":").toLong()
            val numbers = it.substringAfter(":").split(" ").filter { it.isNotEmpty() }.map { it.trim().toLong() }
            Calibration(expectedNumber, numbers)
        }

        return calibrations.sumOf {
            if (canMakeNumber(it.expectedNumber, 0, it.numbers)) it.expectedNumber else 0L
        }


    }

    fun part2(input: List<String>): Long {
        val calibrations = input.map {
            val expectedNumber = it.substringBefore(":").toLong()
            val numbers = it.substringAfter(":").split(" ").filter { it.isNotEmpty() }.map { it.trim().toLong() }
            Calibration(expectedNumber, numbers)
        }

        return calibrations.sumOf {
            if (canMakeNumber(it.expectedNumber, 0, it.numbers)) it.expectedNumber else {
                if (canMakeNumberV2(it.expectedNumber, 0, it.numbers)) it.expectedNumber else 0L
            }
        }
    }


    fun solv(res: Long, args: List<Long>): Boolean {
        return when {
            args.isEmpty() -> res == 0L
            else -> {
                val l = args.last()
                val r = args.dropLast(1)
                if (solv(res - l, r)) return true
                if (res % l == 0L && solv(res / l, r)) return true
                return false

            }
        }
    }

    val input = readInput("Day07")
    val input2 = readInput("Day07")
    part1(input).println()
    //part2(input2).println()

    val calibrations = input.map {
        val expectedNumber = it.substringBefore(":").toLong()
        val numbers = it.substringAfter(":").split(" ").filter { it.isNotEmpty() }.map { it.trim().toLong() }
        Calibration(expectedNumber, numbers)
    }

    kotlin.io.println(calibrations.sumOf {
        if (solv(it.expectedNumber, it.numbers)) {
            if (canMakeNumber(it.expectedNumber, 0, it.numbers) == false) {
                println("1: ${it.expectedNumber} ${it.numbers}")
                return
            }
            it.expectedNumber
        } else {
            if (canMakeNumber(it.expectedNumber, 0, it.numbers)) {
                println("2: ${it.expectedNumber} ${it.numbers}")
                return
            }
            0L
        }
    })
}
