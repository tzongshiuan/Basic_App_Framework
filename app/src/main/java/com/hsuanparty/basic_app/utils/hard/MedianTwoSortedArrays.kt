package com.hsuanparty.basic_app.utils.hard

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/12/11
 * Description:
 */
class MedianTwoSortedArrays {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        if (nums1.size > nums2.size) {
            return findMedianSortedArrays(nums2, nums1)
        }

        var median = 0.0
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
                val max = Math.max(maxLeftX, maxLeftY)
                val min = Math.min(minRightX, minRightY)

                if ((size1 + size2) % 2 == 0) {
                    median = (max + min).toDouble() / 2
                } else {
                    median = max.toDouble()
                }
                break
            } else if (maxLeftY > minRightX) {
                low++
            } else {
                high--
            }
        }

        return median
    }
}