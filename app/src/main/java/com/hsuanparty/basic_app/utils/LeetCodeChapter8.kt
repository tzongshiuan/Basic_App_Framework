package com.hsuanparty.basic_app.utils

import android.graphics.Point
import android.util.Log
import java.util.*
import kotlin.collections.HashMap

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/11/12
 * Description:
 */
class LeetCodeChapter8 {

    fun start() {
        // 8.1 Triple Step
//        Log.d(TAG, "Count ways of 3 steps: ${countWays(3)}")
//        Log.d(TAG, "Count ways of 8 steps: ${countWays(8)}")
//        Log.d(TAG, "Count ways of 15 steps: ${countWays(15)}")

        // 8.2 Robot in Grid
//        val maze = Array<BooleanArray>(5) {
//            BooleanArray(5) { false }
//        }
//        maze[0][0] = true
//        maze[0][1] = true
//        maze[0][2] = true
//        maze[1][2] = true
//        maze[1][3] = true
//        maze[1][4] = true
//        maze[2][4] = true
//        maze[3][4] = true
//        maze[4][4] = true
//        val path = getPath(maze)
//        for (p in path) {
//            Log.d(TAG, "Enter point(${p.x}, ${p.y})")
//        }

        // 8.3 Magic Index
//        val array = IntArray(11)
//        array[0] = -10
//        array[1] = -5
//        array[2] = 2
//        array[3] = 2
//        array[4] = 2
//        array[5] = 3
//        array[6] = 4
//        array[7] = 6
//        array[8] = 9
//        array[9] = 12
//        array[10] = 13
//        Log.d(TAG, "Get magic index: ${magicIndex(array)}")

        // 8.4 Power Set
//        val array = ArrayList<Int>(4)
//        array.add(0)
//        array.add(1)
//        array.add(2)
//        array.add(3)
//        array.add(4)
//        array.add(5)
//        array.add(6)
//        val allSubsets = getSubsets(array)
//        var index = 1
//        for (set in allSubsets) {
//            val builder = StringBuilder()
//            for (value in set) {
//                builder.append("$value ")
//            }
//            Log.d(TAG, "[${index++}]Possible subset is: [$builder]")
//        }

        // 8.5 Recursive Multiply
//        Log.d(TAG, "33 * 17 = ${minProduct(33, 17)}")

        // 8.7 Permutations without Dups
//        val result = getPerms("abcd")
//        for (i in result.indices) {
//            Log.d(TAG, "Permutations of abcd is: ${result[i]}")
//        }

        // 8.8 Permutations with Duplicates
//        val result = getDupPerms("aabbcc")
//        for (i in result.indices) {
//            Log.d(TAG, "Permutations of abcd is: ${result[i]}")
//        }

        // 8.9 Parentheses
//        val result = generateParens(4)
//        Log.d(TAG, "Instance count: ${result.size}")
//        for (i in result.indices) {
//            Log.d(TAG, "Instance of 4 parentheses: ${result[i]}")
//        }

        // 8.10 Paint Fill
//        val screen = Array<Array<Color>>(5) {
//            Array<Color>(5) {
//                Color.Black
//            }
//        }
//        printScreen(screen)
//        paintFill(screen, 2, 2, Color.White)
//        printScreen(screen)
//        paintFill(screen, 2, 2, Color.Red)
//        printScreen(screen)

        // 8.11 Coins
//        Log.d(TAG, "Count ways of 10: ${makeChange(10)}")
//        Log.d(TAG, "Count ways of 100: ${makeChange(100)}")

        // 8.13 Stack of Boxes

    }

    // 8.1
    private fun countWays(n: Int): Int {
        val memo = IntArray(n+1) { -1 }
        return countWays(n, memo)
    }
    private fun countWays(n: Int, memo: IntArray): Int {
        if (n < 0) {
            return 0
        } else if (n == 0) {
            return 1
        } else if (memo[n] > -1) {
            return memo[n]
        }

        memo[n] = countWays(n-1, memo) +
                  countWays(n-2, memo) +
                  countWays(n-3, memo)

        return memo[n]
    }

    // 8.2
    private fun getPath(maze: Array<BooleanArray>): ArrayList<Point> {
        val path = ArrayList<Point>()

        if (maze.isEmpty()) {
            return path
        }

        val set = HashSet<Point>()
        getPath(maze, maze.size-1, maze[0].size-1, path, set)
        return path
    }

    private fun getPath(maze: Array<BooleanArray>, row: Int, col: Int,
                        path: ArrayList<Point>, failSet: HashSet<Point>): Boolean {
        if (row < 0 || col < 0 || !maze[row][col]) {
            return false
        }

        val isOriginPoint = (row == 0) && (col == 0)
        if (isOriginPoint || getPath(maze, row-1, col, path, failSet)
            || getPath(maze, row, col-1, path, failSet)) {
            val point = Point(col, row)
            path.add(point)
            return true
        }

        return false
    }

    // 8.3
    private fun magicIndex(array: IntArray): Int {
        return magicIndex(array, 0, array.size - 1)
    }
    private fun magicIndex(array: IntArray, start: Int, end: Int): Int {
        if (start > end) {
            return -1
        }

        // if distinct
//        val mid = (start + end)/2
//        if (array[mid] == mid) {
//            return mid
//        } else if (array[mid] > mid) {
//            return magicIndex(array, start, mid-1)
//        } else {
//            return magicIndex(array, mid+1, end)
//        }

        // if elements are not distinct
        val midIndex = (start + end) / 2
        val midValue = array[midIndex]
        if (midIndex == midValue) {
            return midIndex
        }

        val leftIndex = Math.min(midIndex-1, midValue)
        val left = magicIndex(array, start, leftIndex)
        if (left > 0) {
            return left
        }

        val rightIndex = Math.max(midIndex+1, midValue)
        var right = magicIndex(array, rightIndex, end)

        return right
    }

    // 8.4
    private fun getSubsets(set: ArrayList<Int>, index: Int): ArrayList<ArrayList<Int>> {
        var allSubsets = ArrayList<ArrayList<Int>>()

        if (set.size == index) {
            allSubsets.add(ArrayList<Int>())    // empty set
        } else {
            allSubsets = getSubsets(set, index+1)
            val element = set[index]
            val moreSubsets = ArrayList<ArrayList<Int>>()
            for (subset in allSubsets) {
                val newSubset = ArrayList<Int>()
                newSubset.addAll(subset)
                newSubset.add(element)
                moreSubsets.add(newSubset)
            }
            allSubsets.addAll(moreSubsets)
        }

        return allSubsets
    }
    private fun convertIntToSet(k: Int, set: ArrayList<Int>): ArrayList<Int> {
        var index = 0
        var bits = k
        val subset = ArrayList<Int>()

        while (bits != 0) {
            if (bits and 1 != 0) {
                subset.add(set[index])
            }
            index++
            bits = bits shr 1
        }

        return subset
    }
    private fun getSubsets(set: ArrayList<Int>): ArrayList<ArrayList<Int>> {
        // recursive
//        return getSubsets(set, 0)

        // iterative
        val allSubsets = ArrayList<ArrayList<Int>>()
        val max = 1 shl set.size
        for (k in 0 until max) {
            val subset = convertIntToSet(k, set)
            allSubsets.add(subset)
        }
        return allSubsets
    }

    // 8.5
    private fun minProductHelper(smaller: Int, bigger: Int): Int {
        if (smaller == 0) {
            return 0
        }
        if (smaller == 1) {
            return bigger
        }

        val s = smaller shr 1
        val half = minProductHelper(s, bigger)

        if (smaller % 2 == 0) {
            return half + half
        } else {
            return half + half + bigger
        }
    }
    private fun minProduct(a: Int, b: Int): Int {
        val bigger = if (a < b) b else a
        val smaller = if (a < b) a else b
        return minProductHelper(smaller, bigger)
    }

    // 8.7
    private fun getPerms(prefix: String, str: String, result: ArrayList<String>) {
        if (str.isEmpty()) {
            result.add(prefix)
            return
        }

        for (i in str.indices) {
            val c = str[i]
            val before = str.substring(0, i)
            val after = str.substring(i+1, str.length)
            getPerms(prefix + c, before + after, result)
        }
    }
    private fun getPerms(str: String): ArrayList<String> {
        val result = ArrayList<String>()
        getPerms("", str, result)
        return result
    }

    // 8.8
    private fun getDupPerms(map: HashMap<Char, Int>, prefix: String,
                            remaining: Int, result: ArrayList<String>) {
        if (remaining == 0) {
            result.add(prefix)
            return
        }

        for (c in map.keys) {
            val count = map[c] ?: 0
            if (count > 0) {
                map[c] = count - 1
                getDupPerms(map, prefix + c, remaining - 1, result)
                map[c] = count
            }
        }
    }
    private fun getDupPerms(str: String): ArrayList<String> {
        val result = ArrayList<String>()
        val map = HashMap<Char, Int>()
        for (i in str.indices) {
            val c = str[i]
            if (!map.contains(c)) {
                map[c] = 0
            }

            map[c] = (map[c] ?: 0) + 1
        }
        getDupPerms(map, "", str.length, result)
        return result
    }

    // 8.9
    private fun addParen(list: ArrayList<String>, leftRem: Int, rightRem: Int,
                         array: CharArray, index: Int) {
        if (leftRem < 0 || leftRem > rightRem) {
            return
        }

        if (leftRem == 0 && rightRem == 0) {
            list.add(array.joinToString(" "))
        } else {
            array[index] = '('
            addParen(list, leftRem-1, rightRem, array, index+1)

            array[index] = ')'
            addParen(list, leftRem, rightRem-1, array, index+1)
        }
    }
    private fun generateParens(count: Int): ArrayList<String> {
        val list = ArrayList<String>()
        var array = CharArray(count*2)
        addParen(list, count, count, array, 0)
        return list
    }

    // 8.10
    enum class Color { Black, White, Red }
    private fun printScreen(screen: Array<Array<Color>>) {
        Log.d(TAG, "printScreen()")
        for (i in screen.indices) {
            val list = screen[i]
            val builder = StringBuilder()
            for (color in list) {
                when (color) {
                    Color.Black -> builder.append("0 ")
                    Color.White -> builder.append("1 ")
                    Color.Red -> builder.append("2 ")
                }
            }
            Log.d(TAG, "$i, Color: $builder")
        }
    }
    private fun paintFill(screen: Array<Array<Color>>, r: Int, c: Int,
                          oColor: Color, nColor: Color): Boolean {
        if (r < 0 || c < 0 || r >= screen.size || c >= screen[0].size) {
            return false
        }

        if (screen[r][c] == oColor) {
            screen[r][c] = nColor

            paintFill(screen, r-1, c, oColor, nColor)
            paintFill(screen, r+1, c, oColor, nColor)
            paintFill(screen, r, c-1, oColor, nColor)
            paintFill(screen, r, c+1, oColor, nColor)
        }

        return true
    }
    private fun paintFill(screen: Array<Array<Color>>, r: Int, c: Int, nColor: Color): Boolean {
        if (screen[r][c] == nColor) {
            return false
        }
        return paintFill(screen, r, c, screen[r][c], nColor)
    }

    // 8.11
    private fun makeChange(n: Int, denom: IntArray, index: Int, map: Array<IntArray>): Int {
        if (map[n][index] > 0) {
            return map[n][index]
        }

        if (index >= denom.size - 1) {
            return 1
        }

        var ways = 0
        var denomAmount = denom[index]
        var count = 0
        while (count <= n) {
            val remaining = n - count
            ways += makeChange(remaining, denom, index + 1, map)
            count += denomAmount
        }

        map[n][index] = ways
        return ways
    }
    private fun makeChange(n: Int): Int {
        val denom = intArrayOf(25, 10, 5, 1)
        val map = Array<IntArray>(n+1) { IntArray(denom.size) }
        return makeChange(n , denom, 0, map)
    }

    // 8.13
    class Box(w: Int, h: Int, d: Int): Comparable<Box> {
        override fun compareTo(other: Box): Int {
            return other.height - height
        }

        var width = w
        var height = h
        var depth = d

        fun canBeAbove(box: Box): Boolean {
            return (box.width < this.width)
                    && (box.height < this.height)
                    && (box.depth < this.depth)
        }
    }
    private fun createStack(boxes: ArrayList<Box>, bottom: Box?, offset: Int, stackMap: IntArray): Int {
        if (offset >= boxes.size) {
            return 0
        }

        val newBottom = boxes[offset]
        var heightWithBottom = 0
        if (bottom == null || newBottom.canBeAbove(bottom)) {
            if (stackMap[offset] == 0) {
                stackMap[offset] = createStack(boxes, newBottom, offset+1, stackMap)
                stackMap[offset] += newBottom.height
            }
            heightWithBottom = stackMap[offset]
        }

        val heightWithout = createStack(boxes, bottom, offset+1, stackMap)

        return Math.max(heightWithBottom, heightWithout)
    }
    private fun createStack(boxes: ArrayList<Box>): Int {
        boxes.sort()
        val stackMap = IntArray(boxes.size)
        return createStack(boxes, null, 0, stackMap)
    }


    companion object {
        private const val TAG = "leetcode"
    }
}