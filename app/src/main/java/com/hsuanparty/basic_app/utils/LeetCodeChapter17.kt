package com.hsuanparty.basic_app.utils

import android.util.Log
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/11/18
 * Description:
 */
class LeetCodeChapter17 {

    fun start() {
        Log.d(TAG, "start()")

        // 17.1 find median from two sorted arrays
        val array1 = intArrayOf(1, 2, 3)
        val array2 = intArrayOf(4, 5, 6)
        Log.d(TAG, "Median is: ${findMedianSortedArrays(array1, array2)}")

        // 17.2 clone graph
        val set = HashSet<Int>(5)
        set.add(5)
        set.contains(5)
    }

    // 17.1
    private fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        if (nums1.size > nums2.size) {
            return findMedianSortedArrays(nums2, nums1)
        }

        val size1 = nums1.size
        val size2 = nums2.size
        var low = 0
        var high = size1

        while (low <= high) {
            val partitionX = (low + high) / 2
            val partitionY = (size1 + size2 + 1) / 2 - partitionX

            val maxLeftX = if (partitionX == 0) Int.MIN_VALUE else nums1[partitionX - 1]
            val maxLeftY = if (partitionY == 0) Int.MIN_VALUE else nums2[partitionY - 1]

            val minRightX = if (partitionX == size1) Int.MAX_VALUE else nums1[partitionX]
            val minRightY = if (partitionY == size2) Int.MAX_VALUE else nums2[partitionY]

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((size1 + size2) % 2 == 0) {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0
                } else {
                    return Math.max(maxLeftX, maxLeftY).toDouble()
                }
            } else if (minRightX < maxLeftY) {
                low = partitionX + 1
            } else {
                high = partitionX - 1
            }
        }

        throw Exception("Failed")
    }

    // 17.2
    class Node(value: Int, neighbors: ArrayList<Node>) {
        var value = value
        val neighbors = neighbors
    }

    private val visited = HashMap<Node, Node>()
    private fun cloneGraph(node: Node?): Node? {
        if (node == null) {
            return null
        }

        if (visited.containsKey(node)) {
            return visited[node]
        }

        val cloneNode = Node(node.value, ArrayList())
        visited[node] = cloneNode

        for (neighbor in node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor)!!)
        }

        return cloneNode
    }

    companion object {
        private const val TAG = "leetcode"
    }
}