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
        val array = ArrayList<Int>(4)
        array.add(0)
        array.add(1)
        array.add(2)
        array.add(3)
        array.add(4)
        array.add(5)
        array.add(6)
        val allSubsets = getSubsets(array)
        var index = 1
        for (set in allSubsets) {
            val builder = StringBuilder()
            for (value in set) {
                builder.append("$value ")
            }
            Log.d(TAG, "[${index++}]Possible subset is: [$builder]")
        }
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
        val subset = ArrayList<Int>()

        return subset
    }
    private fun getSubsets(set: ArrayList<Int>): ArrayList<ArrayList<Int>> {
        // recursive
        return getSubsets(set, 0)

        // iterative
    }

    companion object {
        private const val TAG = "leetcode"
    }
}