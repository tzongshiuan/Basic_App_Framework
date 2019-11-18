package com.hsuanparty.basic_app.utils

import android.util.Log
import java.util.Collections.swap

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/11/15
 * Description:
 */
class LeetCodeChapter10 {

    fun start() {
        // 10.1 Sorted Merge
//        val a = IntArray(9)
//        a[0] = 1
//        a[1] = 3
//        a[2] = 5
//        a[3] = 7
//        a[4] = 9
//        val b = intArrayOf(2, 4, 6, 8)
//        sortedMerge(a, b, 5, 4)
//
//        val builder = StringBuilder()
//        for (i in a.indices) {
//            builder.append("${a[i]} ")
//        }
//        Log.d(TAG, "Sort merge result: $builder")

        // 10.2 Group Anagrams
//        val array = Array<String>(6) { "" }
//        array[0] = "abcd"
//        array[1] = "zxcv"
//        array[2] = "rewq"
//        array[3] = "vcxz"
//        array[4] = "qwer"
//        array[5] = "dcba"
//        sortAnagrams(array)
//        for (i in array.indices) {
//            Log.d(TAG, "$i, sor result: ${array[i]}")
//        }

        // 10.3 Search in Rotated Array
//        val array = intArrayOf(50, 5, 20, 30, 40)
//        Log.d(TAG, "index of array with target 50 is: ${searchRotate(array, 50)}")
//        Log.d(TAG, "index of array with target 30 is: ${searchRotate(array, 30)}")

        // 10.4 Sorted Search, No Size

        // 10.5 Sparse Search
//        val array = arrayOf("at", "", "", "", "ball", "", "", "car", "", "", "dad", "", "")
//        Log.d(TAG, "Search ball in sparse array at index: ${searchSparse(array, "ball")}")
//        Log.d(TAG, "Search car in sparse array at index: ${searchSparse(array, "car")}")
//        Log.d(TAG, "Search dad in sparse array at index: ${searchSparse(array, "dad")}")

        // 10.8 Find Duplicates
//        val array = intArrayOf(20, 21, 22, 23, 23, 24, 25, 26, 26, 27, 28, 29, 30, 31998, 31999, 31999)
//        val list = checkDuplicates(array)
//        val builder = StringBuilder()
//        for (num in list) {
//            builder.append("$num ")
//        }
//        Log.d(TAG, "Duplicates: $builder")

        // 10.9 Sorted Matrix Search
//        val matrix = arrayOf(intArrayOf(0, 1, 2, 3), intArrayOf(4, 5, 6, 7), intArrayOf(8, 9, 10, 11))
//        val pos = findElement(matrix, 6)
//        Log.d(TAG, "Target position of 6: (${pos?.col}, ${pos?.row})")
//        val pos2 = findElement(matrix, 11)
//        Log.d(TAG, "Target position of 11: (${pos2?.col}, ${pos2?.row})")

        // 10.11 Peaks and Valleys
        val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        sortValleyPeak(array)
        val builder = StringBuilder()
        for (num in array) {
            builder.append("$num ")
        }
        Log.d(TAG, "After peak and valley sort, result: $builder")
    }

    private fun mergeSort(array: IntArray) {
        val helper = IntArray(array.size)
        mergeSort(array, helper, 0, array.size - 1)
    }
    private fun mergeSort(array: IntArray, helper: IntArray, start: Int, end: Int) {
        if (start >= end) {
            return
        }

        val mid = (start + end) / 2
        mergeSort(array, helper, start, mid)
        mergeSort(array, helper, mid + 1, end)
        merge(array, helper, start, mid, end)
    }
    private fun merge(array: IntArray, helper: IntArray, start: Int, mid: Int, end: Int) {
        for (i in start..end) {
            helper[i] = array[i]
        }

        var helperLeft = start
        var helperRight = mid + 1
        var current = start

        while (helperLeft <= mid && helperRight <= end) {
            if (helper[helperLeft] < helper[helperRight]) {
                array[current] = helper[helperLeft]
                helperLeft++
            } else {
                array[current] = helper[helperRight]
                helperRight++
            }

            current++
        }

        // The right half doesn't need to be copied because it's already there.
        val remaining = mid - helperLeft
        for (i in 0 until remaining) {
            array[current + i] = helper[helperLeft + i]
        }
    }

    private fun quickSort(array: IntArray) {
        quickSort(array, 0, array.size - 1)
    }
    private fun quickSort(array: IntArray, start: Int, end: Int) {
        val index = partition(array, start, end)
        if (start < index - 1) {
            quickSort(array, start, index - 1)
        }

        if (index < end) {
            quickSort(array, index, end)
        }
    }
    private fun partition(array: IntArray, s: Int, e: Int): Int {
        var start = s
        var end = e
        val pivot = array[(start+end)/2]

        while (start <= end) {
            while (array[start] < pivot) start++
            while (array[end] > pivot) end--

            if (start <= end) {
                val temp = array[start]
                array[start] = array[end]
                array[end] = temp
                start++
                end--
            }
        }
        return start
    }

    // 10.1
    private fun sortedMerge(a: IntArray, b: IntArray, lastA: Int, lastB: Int) {
        var lastIndex = lastA + lastB - 1
        var indexA = lastA - 1
        var indexB = lastB - 1

        while (indexA >= 0 && indexB >= 0) {
            if (a[indexA] < b[indexB]) {
                a[lastIndex] = b[indexB]
                indexB--
            } else {
                a[lastIndex] = a[indexA]
                indexA--
            }
            lastIndex--
        }
    }

    // 10.2
    private fun sortChars(s: String): String {
        val chars = s.toCharArray()
        chars.sort()
        return chars.joinToString()
    }
    private fun sortAnagrams(array: Array<String>) {
        val mapList = HashMap<String, ArrayList<String>>()

        for (s in array) {
            val key = sortChars(s)
            if (!mapList.contains(key)) {
                mapList[key] = ArrayList<String>()
            }
            mapList[key]?.add(s)
        }

        var index = 0
        for (key in mapList.keys) {
            val list = mapList[key]
            if (list != null) {
                for (s in list) {
                    array[index] = s
                    index++
                }
            }
        }
    }

    // 10.3
    private fun searchRotate(array: IntArray, target: Int): Int {
        return searchRotate(array, 0, array.size - 1, target)
    }
    private fun searchRotate(array: IntArray, left: Int, right: Int, target: Int): Int {
        if (left > right) {
            return -1
        }

        val mid = (left + right) / 2
        if (array[mid] == target) {
            return mid
        }

        if (array[left] < array[mid]) {
            if (target in array[left] until array[mid]) {
                return searchRotate(array, left, mid - 1, target)
            } else {
                return searchRotate(array, mid + 1, right, target)
            }
        } else if (array[mid] < array[right]) {
            if (target in (array[mid] + 1)..array[right]) {
                return searchRotate(array, mid + 1, right, target)
            } else {
                return searchRotate(array, left, mid - 1, target)
            }
        } else if (array[left] == array[mid]) {
            if (array[mid] != array[right]) {
                return searchRotate(array, mid + 1, right, target)
            } else {
                val result = searchRotate(array, left, mid - 1, target)
                if (result == -1) {
                    return searchRotate(array, mid + 1, right, target)
                } else {
                    return result
                }
            }
        }

        return -1
    }

    // 10.5
    private fun searchSparse(strings: Array<String>, target: String): Int {
        if (strings.isEmpty() || target.isEmpty()) {
            return -1
        }

        return searchSparse(strings, target, 0, strings.size - 1)
    }
    private fun searchSparse(strings: Array<String>, target: String, start: Int, end: Int): Int {
        if (start > end) {
            return -1
        }

        var mid = (start + end) / 2
        if (strings[mid].isEmpty()) {
            var left = mid - 1
            var right = mid + 1
            while (true) {
                if (left < start && right > end) {
                    return -1
                } else if (left >= start && strings[left].isNotEmpty()) {
                    mid = left
                    break
                } else if (right <= end && strings[right].isNotEmpty()) {
                    mid = right
                    break
                }

                left--
                right++
            }
        }

        if (strings[mid] == target) {
            return mid
        } else if (strings[mid].compareTo(target) > 0) {
            return searchSparse(strings, target, start, mid - 1)
        } else {
            return searchSparse(strings, target, mid + 1, end)
        }
    }

    // 10.8
    class BitSet(size: Int) {
        var bitset: IntArray = IntArray((size shr 5) + 1)

        fun get(num: Int): Boolean {
            val wordNum = num shr 5
            val bitNum = num and 0x1f
            return bitset[wordNum] and (1 shl bitNum) != 0
        }

        fun set(num: Int) {
            val wordNum = num shr 5
            val bitNum = num and 0x1f
            bitset[wordNum] = bitset[wordNum] or (1 shl bitNum)
        }
    }
    private fun checkDuplicates(array: IntArray): ArrayList<Int> {
        val result = ArrayList<Int>()
        val bs = BitSet(32000)
        for (i in array.indices) {
            val num = array[i]
            val num0 = num - 1
            if (bs.get(num0)) {
                result.add(num)
            } else {
                bs.set(num0)
            }
        }

        return result
    }

    // 10.9
    class Coordinate(r: Int, c: Int) {
        var row = r
        var col = c

        fun inbounds(matrix: Array<IntArray>): Boolean {
            return row >= 0 && col >= 0
                    && row < matrix.size && col < matrix[0].size
        }

        fun isBefore(p: Coordinate): Boolean {
            return row <= p.row && col <= p.col
        }

        fun clone(): Coordinate {
            return Coordinate(row, col)
        }

        fun setToAverage(min: Coordinate, max: Coordinate) {
            row = (min.row + max.row) / 2
            col = (min.col + max.col) / 2
        }
    }
    private fun findElement(matrix: Array<IntArray>, x: Int): Coordinate? {
        val origin = Coordinate(0, 0)
        val dest = Coordinate(matrix.size - 1, matrix[0].size - 1)
        return findElement(matrix, origin, dest, x)
    }
    private fun findElement(matrix: Array<IntArray>, origin: Coordinate, dest: Coordinate, x: Int): Coordinate? {
        if (!origin.inbounds(matrix) || !dest.inbounds(matrix)) {
            return null
        }
        if (matrix[origin.row][origin.col] == x) {
            return origin
        } else if (!origin.isBefore(dest)) {
            return null
        }

        val start = origin.clone()
        val dist = Math.min(dest.row - origin.row, dest.col - origin.col)
        val end = Coordinate(origin.row + dist, origin.col + dist)
        val p = Coordinate(0, 0)

        while (start.isBefore(end)) {
            p.setToAverage(start, end)
            if (x > matrix[p.row][p.col]) {
                start.row = p.row + 1
                start.col = p.col + 1
            } else {
                end.row = p.row - 1
                end.col = p.col - 1
            }
        }

        return partitionSearch(matrix, origin, dest, start, x)
    }
    private fun partitionSearch(matrix: Array<IntArray>, origin: Coordinate,
                                 dest: Coordinate, pivot: Coordinate, x: Int): Coordinate? {
        val lowerLeftOrigin = Coordinate(pivot.row, origin.col)
        val lowerLeftDest = Coordinate(dest.row, pivot.col - 1)
        val upperRightOrigin = Coordinate(origin.row, pivot.col)
        val upperRightDest = Coordinate(pivot.row - 1, dest.col)

        val lowerLeft = findElement(matrix, lowerLeftOrigin, lowerLeftDest, x)
        if (lowerLeft == null) {
            return findElement(matrix, upperRightOrigin, upperRightDest, x)
        }

        return lowerLeft
    }

    // 10.11
    private fun sortValleyPeak(array: IntArray) {
        if (array.size < 3) {
            return
        }

        for (i in 1 until array.size step 2) {
            val biggestIndex = maxIndex(array, i-1, i, i+1)
            if (i != biggestIndex) {
                val temp = array[biggestIndex]
                array[biggestIndex] = array[i]
                array[i] = temp
            }
        }
    }
    private fun maxIndex(array: IntArray, a: Int, b: Int, c: Int): Int {
        val len = array.size
        val valueA = if (a in 0 until len) array[a] else Int.MIN_VALUE
        val valueB = if (a in 0 until len) array[a] else Int.MIN_VALUE
        val valueC = if (a in 0 until len) array[a] else Int.MIN_VALUE

        val max = Math.max(valueA, Math.max(valueB, valueC))

        if (valueA == max) {
            return a
        } else if (valueB == max) {
            return b
        } else {
            return c
        }
    }

    companion object {
        private const val TAG = "leetcode"
    }
}