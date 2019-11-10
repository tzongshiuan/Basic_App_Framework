package com.hsuanparty.basic_app.utils

import android.util.Log
import kotlin.experimental.or

class LeetCodeChapter5 {

    fun start() {
        // 5.1 Insertion
//        Log.d(TAG, "Insert 48 with 12 is: ${insertBits(48, 3, 2, 3)}") // should be 60

        // 5.2 Binary to String
//        Log.d(TAG, "12 to string: ${printBinary(12.0)}")
//        Log.d(TAG, "0.75 to string: ${printBinary(0.625)}")
//        Log.d(TAG, "0.66 to string: ${printBinary(0.66)}")

        // 5.3, suppose result to 6
//        Log.d(TAG, "Flip Longest length of (1767)11011100111: ${flipBit(1767)}")

        // 5.4 Next Number
//        Log.d(TAG, "24's next number is: ${getNext(24)}")  // 011000 => 100001(33)
//        Log.d(TAG, "24's previous number is: ${getPrev(24)}")  // 011000 => 010100(20)

        // 5.5 Debugger
        // (n and (n-1) == 0) means n is a power of 2(or n is 0)

        // 5.6 Conversion
//        Log.d(TAG, "Bit swap needed count from 29 to 15 is: ${bitSwapRequired(29, 15)}")  // 2

        // 5.7 Pair Wise Swap
//        Log.d(TAG, "After swap odd and even bits, 10 become: ${swapBits(10)}") // 5
//        Log.d(TAG, "After swap odd and even bits, 20 become: ${swapBits(20)}") // 40

        // 5.8 Draw Line
        val byteArray = byteArrayOf(
            0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00
        )
        drawLine(byteArray, 32, 4, 27, 1)

        for (i in byteArray.indices step 4) {
            val builder = StringBuilder("Index: $i, ")
            builder.append("${byteArray[i].toInt()} ")
            builder.append("${byteArray[i+1].toInt()} ")
            builder.append("${byteArray[i+2].toInt()} ")
            builder.append("${byteArray[i+3].toInt()} ")
            Log.d(TAG, builder.toString())
        }
    }

    // 5.1
    private fun insertBits(n: Int, m: Int, i: Int, j: Int): Int {
        val allOnes = 0.inv()
        val left = allOnes shl (j + 1)
        val right = (1 shl i) - 1
        val mask = left or right
        val clearValue = n and mask
        val insertValue = m shl i

        return clearValue or insertValue
    }

    // 5.2
    private fun printBinary(num: Double): String {
        if (num >= 1 || num <= 0) {
            return "ERROR"
        }

        var current = num
        val builder = StringBuilder()
        builder.append("0.")

        while (current > 0) {
            if (builder.length > 32) {
                return "ERROR"
            }

            current *= 2
            if (current >= 1) {
                builder.append("1")
                current -= 1
            } else {
                builder.append("0")
            }
        }

        return builder.toString()
    }

    // 5.3
    private fun flipBit(num: Int): Int {
        if (num.inv() == 0) {
            return 32
        }

        var currentLength = 0
        var previousLength = 0
        var a = num
        var maxLength = 1

        while (a != 0) {
            if (a and 1 != 0) {
                currentLength++
            } else {
                previousLength = if (a and 2 == 0) 0 else currentLength
                currentLength = 0
            }

            maxLength = Math.max(currentLength + previousLength + 1, maxLength)
            a = a ushr 1
        }

        return maxLength
    }

    // 5.4
    private fun getNext(num: Int): Int {
        var c = num
        var c0 = 0
        var c1 = 0

        while ((c and 1 == 0) && (c != 0)) {
            c0++
            c = c shr 1
        }
        while (c and 1 == 1) {
            c1++
            c = c shr 1
        }

        if ((c0 + c1 == 31) || (c0 + c1 == 0)) {
            return -1
        }

        val p = c0 + c1
        c = num

        c = c or (1 shl p)
        c = c and ((1 shl p) - 1).inv()
        c = c or (1 shl (c1-1) - 1)

        return c
    }
    private fun getPrev(num: Int): Int {
        var c = num
        var c0 = 0
        var c1 = 0

        while (c and 1 != 0) {
            c1++
            c = c shr 1
        }

        if (c == 0) {
            return -1
        }

        while (c and 1 == 0 && c != 0) {
            c0++
            c = c shr 1
        }

        val p = c0 + c1
        c = num

        c = c and (0.inv() shl (p + 1))
        val mask = (1 shl (c1+1)) - 1
        c = c or (mask shl (c0-1))

        return c
    }

    // 5.6
    private fun bitSwapRequired(a: Int, b: Int): Int {
        var count = 0
        var xorValue = a xor b

        while (xorValue != 0) {
//            if (xorValue and 1 != 0) {
//                count++
//            }
//            xorValue = xorValue shr 1

            count++
            xorValue = xorValue and (xorValue - 1)
        }

        return count
    }

    // 5.7
    private fun swapBits(x: Int): Int {
        return ((x and 0x2aaaaaaa) ushr 1) or ((x and 0x55555555) shl 1)
    }

    // 5.8
    private fun drawLine(screen: ByteArray, width: Int, x1: Int, x2: Int, y: Int) {
        val startOffset = x1 % 8
        var firstFull = x1 / 8
        if (startOffset != 0) {
            firstFull++
        }

        val endOffset = x2 % 8
        var lastFull = x2 / 8
        if (endOffset != 7) {
            lastFull--
        }

        for (b in firstFull..lastFull) {
            screen[(width/8)*y + b] = 0xFF.toByte()
        }

        val startMask = 0xFF shr startOffset
        val endMask = (0xFF shr (endOffset+1)).inv() and 0xFF

        if (x1/8 == x2/8) {
            val mask = startMask and endMask
            val pos = (width/8)*y + x1/8
            screen[pos] = screen[pos] or mask.toByte()
        } else {
            if (startOffset != 0) {
                val pos = (width/8)*y + firstFull - 1
                screen[pos] = screen[pos] or startMask.toByte()
            }

            if (endOffset != 7) {
                val pos = (width/8)*y + lastFull + 1
                screen[pos] = screen[pos] or endMask.toByte()
            }
        }
    }

    companion object {
        private const val TAG = "leetcode"
    }
}