package com.hsuanparty.basic_app.utils

import android.provider.MediaStore
import android.util.Log

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/11/18
 * Description:
 */
class LeetCodeChapter16 {

    fun start() {
        Log.d(TAG, "start()")

        // 16.1 Number Swapper
//        val a = 4
//        val b = 9
//        numberSwapper(a, b)

        // 16.6 Smallest Difference
//        val array1 = intArrayOf(1, 3, 15, 11, 2)
//        val array2 = intArrayOf(23, 127, 245, 19, 8)
//        val result = findSmallestDiff(array1, array2)
//        Log.d(TAG, "Smallest Difference: $result")

        // 16.17 Contiguous Sequence
//        val array = intArrayOf(2, -8, 3, -2, 4, -10)
//        Log.d(TAG, "Maximum contiguous num: ${getMaxContiNum(array)}")

        // 16.21 Sum Swap
        val array1 = intArrayOf(4, 1, 2, 1, 1, 2)
        val array2 = intArrayOf(3, 6, 3, 3)
        val result = findSwapValue(array1, array2)
        Log.d(TAG, "${result[0]}")
        Log.d(TAG, "${result[1]}")
    }

    // 16.1
    private fun numberSwapper(a: Int, b: Int) {
//        var n1 = b - a
//        var n2 = b - n1
//        n1 += n2

        var n1 = a xor b
        var n2 = n1 xor b
        n1 = n1 xor n2

        Log.d(TAG, "n1: $n1, n2: $n2")
    }

    // 16.6
    private fun findSmallestDiff(array1: IntArray, array2: IntArray): Int {
        if (array1.isEmpty() || array2.isEmpty()) {
            return -1
        }

        array1.sort()
        array2.sort()

        var index1 = 0
        var index2 = 0
        var min = Int.MAX_VALUE

        while (index1 < array1.size && index2 < array2.size) {
            val diff = Math.abs(array1[index1] - array2[index2])
            if (diff < min) {
                min = diff
            }

            if (min == 0) {
                break
            }

            if (array1[index1] < array2[index2]) {
                index1++
            } else {
                index2++
            }
        }

        return min
    }

    // 16.17
    private fun getMaxContiNum(array: IntArray): Int {
        var maxSum = 0
        var sum = 0

        for (i in array.indices) {
            sum += array[i]
            if (sum > maxSum) {
                maxSum = sum
            }

            if (sum < 0) {
                sum = 0
            }
        }

        return maxSum
    }

    // 16.21
    private fun findSwapValue(array1: IntArray, array2: IntArray): IntArray {
        return intArrayOf(-1, -1)
    }


    companion object {
        private const val TAG = "leetcode"
    }
}