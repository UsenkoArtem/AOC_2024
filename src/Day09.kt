fun main() {
    fun part1(input: String): Long {
        val blocks = mutableListOf<Int>()
        var fileId = 0
        var isFile = true
        for (i in input.indices) {
            val length = input[i].digitToInt()
            repeat(length) {
                blocks.add(if (isFile) fileId.toInt() else -1)
            }
            if (isFile) fileId++
            isFile = !isFile
        }

        // Step 2: Simulate the compaction process
        var lastFreeIndex = blocks.indexOf(-1)
        while (lastFreeIndex != -1) {
            val nextFileIndex = blocks.indexOfLast { ch -> ch != -1 }
            if (nextFileIndex == -1 || nextFileIndex < lastFreeIndex) break
            // Move the block to the free space
            blocks[lastFreeIndex] = blocks[nextFileIndex]
            blocks[nextFileIndex] = -1
            // Update last free index
            lastFreeIndex = blocks.indexOf(-1)
        }

        // Step 3: Calculate the checksum
        var checksum = 0L
        blocks.forEachIndexed { index, block ->
            if (block != -1) {
                checksum += index * block
            }
        }
        return checksum.toLong()
    }

    fun part2(input: String): Long {
        val blocks = mutableListOf<Int>()
        val blocksSizeMap = mutableMapOf<Int, Int>()
        var fileId = 0
        var isFile = true
        for (i in input.indices) {
            val length = input[i].digitToInt()
            repeat(length) {
                blocks.add(if (isFile) fileId.toInt() else -1)
                if (isFile) {
                    blocksSizeMap[fileId] = blocksSizeMap.getOrDefault(fileId, 0) + 1
                }
            }
            if (isFile) fileId++
            isFile = !isFile
        }

        fun getRangeOfFreeBlocks(blocks: List<Int>, start: Int): IntRange {
            var left = start
            while (left < blocks.size && blocks[left] != -1) {
                left++
            }
            if (left >= blocks.size || blocks[left] != -1) return IntRange.EMPTY
            var right = left
            while (right < blocks.size && blocks[right] == -1) {
                right++
            }
            return left until right
        }

        fun getRangeOfFileBlocks(blocks: List<Int>, fileId: Int): IntRange {
            var left = blocks.indexOf(fileId)
            if (left == -1) return IntRange.EMPTY
            var right = left
            while (right < blocks.size && blocks[right]==fileId) {
                right++
            }
            return left until right
        }


        val fileIds = blocksSizeMap.keys.sortedDescending()
        println(blocks.joinToString("") { if (it == -1) "." else it.toString() })
        for (fileId in fileIds) {
            val fileIndexBlockRange = getRangeOfFileBlocks(blocks, fileId)

            val blockSize = blocksSizeMap[fileId]!!

            var rangeFreeBlocks = getRangeOfFreeBlocks(blocks, 0)
            while (true) {
                if (blockSize <= rangeFreeBlocks.count() && fileIndexBlockRange.last > rangeFreeBlocks.first) {
                    if (fileIndexBlockRange == IntRange.EMPTY) break

                    // Move the block to the free space
                    for (i in 0 until blockSize) {
                        blocks[rangeFreeBlocks.first + i] = blocks[fileIndexBlockRange.first + i]
                    }
                    // Fill the rest with -1
                    for (i in blockSize until rangeFreeBlocks.count()) {
                        blocks[rangeFreeBlocks.first + i] = -1
                    }
                    // Update the file blocks
                    for (i in fileIndexBlockRange) {
                        blocks[i] = -1
                    }
                    break
                }
                rangeFreeBlocks = getRangeOfFreeBlocks(blocks, rangeFreeBlocks.last + 1)
                if (rangeFreeBlocks == IntRange.EMPTY) break
            }
        }


        println(blocks.joinToString("") { if (it == -1) "." else it.toString() })
        var checksum = 0L
        blocks.forEachIndexed { index, block ->
            if (block != -1) {
                checksum += index * block
            }
        }
        return checksum.toLong()
    }


    val input = readInput("Day09")
    val input2 = readInput("Day09")
    part1(input.first()).println()
    part2(input2.first()).println()


}
