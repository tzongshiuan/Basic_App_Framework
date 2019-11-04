package com.hsuanparty.basic_app.utils

import android.util.Log
import java.lang.StringBuilder
import java.util.*

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/11/4
 * Description:
 */
class LeetCodeChapter1 {
    fun start() {
        var testInput = "a b c d e"
        var testInput2 = "eabdc"

        // 1.1 isUnique
//        val isUnique = isUnique(testInput)
//        Log.d(TAG, "isUnique String: $isUnique")

        // 1.2 Check Permutation
//        val check = checkPermutation(testInput, testInput2)
//        Log.d(TAG, "check permutation: $check")

        // 1.3 URL
//        val test1_3 = CharArray(100)
//        test1_3[0] = 'a'
//        test1_3[1] = ' '
//        test1_3[2] = 'b'
//        test1_3[3] = ' '
//        test1_3[4] = 'c'
//        val out = strToUrl(test1_3, 5)
//        Log.d(TAG, "url from string is: $out")

        // 1.4 Permutation of Palindrome
//        Log.d(TAG, "is Permutation of Palindrome: ${isPermutationOfPalindrome("Tact Coa")}")

        // 1.5 One Away
//        Log.d(TAG, "is one away: ${isOneAway("pale","ple")}")
//        Log.d(TAG, "is one away: ${isOneAway("pale","bae")}")

        // 1.6 String Compression
//        Log.d(TAG, "String after compression is: ${stringCompressed("aabcccccaaa")}")
//        Log.d(TAG, "String after compression is: ${stringCompressed("abcdefghijk")}")

        // 1.7 Rotate Matrix
//        val afterRotate = rotateMatrix(
//            arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
//        for (i in afterRotate.indices) {
//            var print = ""
//            for (j in afterRotate[i].indices) {
//                print += afterRotate[i][j]
//            }
//            Log.d(TAG, "row $i: $print")
//        }

        // 1.8 Zero Matrix
        val afterRotate = zeroMatrix(
            arrayOf(intArrayOf(1, 2, 3, 0), intArrayOf(4, 5, 6, 7), intArrayOf(8, 9, 9, 11)))
        for (i in afterRotate.indices) {
            var print = ""
            for (j in afterRotate[i].indices) {
                print += afterRotate[i][j]
            }
            Log.d(TAG, "row $i: $print")
        }

        // 1.9 String Rotation
        Log.d(TAG, "is String rotation: ${isStringRotation("abcde", "aebcd")}")
        Log.d(TAG, "is String rotation: ${isStringRotation("waterbottle", "erbottlewat")}")
    }

    // 1.1
    private fun isUnique(str: String): Boolean {
        if(str.length > 128) {
            return false
        }

        // Solution 1
//        val charArray = BooleanArray(128)
//        for (i in 0 until str.length) {
//            val c = str[i]
//            if (charArray[c.toInt()]) {
//                return false
//            }
//
//            charArray[c.toInt()] = true
//        }
//        return true

        // Solution 2, bit vector and assume lower case letters only
//        var checker = 0
//        for (i in str.indices) {
//            val shift = str[i].toInt() - 'a'.toInt()
//
//            if ((checker and (1 shl shift)) > 0) {
//                return false
//            }
//            checker = checker or (1 shl shift)
//        }
//        return true

        // Solution 3, sort and compare
        val charArray = str.toCharArray()
        charArray.sort()

        for (i in 0 until charArray.size - 1) {
            if (charArray[i] == charArray[i + 1]) {
                return false
            }
        }

        return true
    }

    // 1.2
    private fun checkPermutation(s1: String, s2: String): Boolean {
        if (s1.length != s2.length) {
            return false
        }

        // solution 1, O(nlog(n))
//        val arr1 = s1.toCharArray()
//        var arr2 = s2.toCharArray()
//        arr1.sort()
//        arr2.sort()
//
//        for (i in arr1.indices) {
//            if (arr1[i] != arr2[i]) {
//                return false
//            }
//        }
//        return true

        // solution 2, O(n)
        val countArray = IntArray(128)

        for (i in s1.indices) {
            val c = s1[i]
            countArray[c.toInt()]++
        }

        for (i in s2.indices) {
            val c = s2[i]
            if (countArray[c.toInt()] == 0) {
                return false
            }

            countArray[c.toInt()]--
        }

        return true
    }

    // 1.3
    private fun strToUrl(str: CharArray, length: Int): String {
        // count spaces first
        var spaceCount = 0
        for (i in 0 until length) {
            if (str[i] == ' ') {
                spaceCount++
            }
        }

        // replace space with %20
        for (i in (length - 1) downTo 0) {
            if (str[i] == ' ') {
                spaceCount--
                val base = i + spaceCount*2
                str[base] = '%'
                str[base + 1] = '2'
                str[base + 2] = '0'
            } else {
                str[i + spaceCount*2] = str[i]
            }
        }

        return str.joinToString()
    }

    // 1.4
    private fun getCharIndex(c: Char): Int {
        if (c.toInt() <= 'z'.toInt() && c.toInt() >= 'a'.toInt()) {
            return c.toInt() - 'a'.toInt()
        } else if (c.toInt() <= 'Z'.toInt() && c.toInt() >= 'A'.toInt()) {
            return c.toInt() - 'A'.toInt()
        }
        return -1
    }
    private fun isPermutationOfPalindrome(str: String): Boolean {
        // solution 1, hash table
//        var countOdd = 0
//        val countArray = IntArray(52)
//
//        for (i in str.indices) {
//            val index = getCharIndex(str[i])
//
//            if (index != -1) {
//                countArray[index]++
//
//                if (countArray[index] % 2 == 0) {
//                    countOdd--
//                } else {
//                    countOdd++
//                }
//            }
//        }
//        return countOdd <= 1

        // solution 2, bit vector
        val lowerStr = str.toLowerCase(Locale.getDefault())
        var checker = 0

        for (i in lowerStr.indices) {
            val index = getCharIndex(lowerStr[i])
            if (index != -1) {
                val bit = (1 shl index)
                checker = checker xor bit
            }
        }

        return (checker and (checker - 1)) == 0
    }

    // 1.5
    private fun isOneAway(s1: String, s2: String): Boolean {
        if (Math.abs(s1.length - s2.length) > 1) {
            return false
        }

        val first = if (s1.length >= s2.length) s1 else s2
        val second = if (s1.length < s2.length) s1 else s2
        var isDiff = false
        var index1 = 0
        var index2 = 0

        while (index1 < first.length && index2 < second.length) {
            if (first[index1] != second[index2]) {
                if (isDiff) {
                    return false
                }
                isDiff = true

                index1++
                if (s1.length == s2.length) {
                    index2++
                }
            } else {
                index1++
                index2++
            }
        }
        return true
    }

    // 1.6
    private fun countCompressed(str: String): Int {
        var countLength = 0
        var count = 0

        for (i in str.indices) {
            count++
            if (((i + 1) >= str.length) || str[i] != str[i+1]) {
                countLength += 1 + count.toString().length
                count = 0
            }
        }

        return countLength
    }
    private fun stringCompressed(str: String): String {
        // special check here to optimize case that we don't have a large number of repeating characters
        val length = countCompressed(str)
        if (str.length < length) {
            return str
        }

        val builder = StringBuilder(length)
        var count = 0

        for (i in str.indices) {
            count++
            if (((i + 1) >= str.length) || str[i] != str[i+1]) {
                builder.append(str[i])
                builder.append(count)
                count = 0
            }
        }

        return builder.toString()
    }

    // 1.7
    private fun rotateMatrix(matrix: Array<IntArray>): Array<IntArray> {
        if (matrix.isEmpty() || matrix[0].size != matrix.size) {
            return matrix
        }

        val n = matrix.size
        for (layer in 0 until n/2) {
            val first = layer
            val last = n - 1 - layer

            for (i in first until last) {
                val offset = i - first
                val temp = matrix[first][i]
                matrix[first][i] = matrix[last - offset][first]
                matrix[last - offset][first] = matrix[last][last - offset]
                matrix[last][last - offset] = matrix[i][last]
                matrix[i][last] = temp
            }
        }

        return matrix
    }

    // 1.8
    private fun nullifyColumn(matrix: Array<IntArray>, column: Int) {
        for (i in matrix.indices) {
            matrix[i][column] = 0
        }
    }

    private fun nullifyRow(matrix: Array<IntArray>, row: Int) {
        for (i in matrix[0].indices) {
            matrix[row][i] = 0
        }
    }

    private fun zeroMatrix(matrix: Array<IntArray>): Array<IntArray> {
        if (matrix.isEmpty()) {
            return matrix
        }

        // brute force, O(n*n)
//        val ret = Array(matrix.size) {
//            IntArray(matrix[0].size)
//        }
//        for (i in matrix.indices) {
//            for (j in matrix[i].indices) {
//                ret[i][j] = matrix[i][j]
//            }
//        }
//
//        for (i in matrix.indices) {
//            for (j in matrix[i].indices) {
//                if (matrix[i][j] == 0) {
//                    for (k in matrix.indices) {
//                        ret[k][j] = 0
//                    }
//                    for (k in matrix[i].indices) {
//                        ret[i][k] = 0
//                    }
//                }
//            }
//        }
//        return ret

        // solution 2
//        val rowChecker = BooleanArray(matrix.size)
//        val columnChecker = BooleanArray(matrix[0].size)
//
//        for (i in matrix.indices) {
//            for (j in matrix[0].indices) {
//                if (matrix[i][j] == 0) {
//                    rowChecker[i] = true
//                    columnChecker[j] = true
//                }
//            }
//        }
//
//        for (i in rowChecker.indices) {
//            if (rowChecker[i]) nullifyRow(matrix, i)
//        }
//
//        for (i in columnChecker.indices) {
//            if (columnChecker[i]) nullifyColumn(matrix, i)
//        }
//
//        return matrix

        // best solution with lowest space O(1)
        var rowHasZero = false
        var colHasZero = false

        for (i in matrix[0].indices) {
            if (matrix[0][i] == 0) {
                rowHasZero = true
            }
        }

        for (i in matrix.indices) {
            if (matrix[i][0] == 0) {
                colHasZero = true
            }
        }

        for (i in 1 until matrix.size) {
            for (j in 1 until matrix.size) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0
                    matrix[i][0] = 0
                }
            }
        }

        for (i in matrix.indices) {
            if (matrix[i][0] == 0) {
                nullifyRow(matrix, i)
            }
        }

        for (i in matrix[0].indices) {
            if (matrix[0][i] == 0) {
                nullifyColumn(matrix, i)
            }
        }

        if (rowHasZero) {
            nullifyRow(matrix, 0)
        }

        if (colHasZero) {
            nullifyColumn(matrix, 0)
        }

        return matrix
    }

    // 1.9
    private fun isSubString(s1: String, s2: String): Boolean {
        return s1.contains(s2)
    }

    private fun isStringRotation(s1: String, s2: String): Boolean {
        return true
    }

    companion object {
        private const val TAG = "leetcode"
    }
}