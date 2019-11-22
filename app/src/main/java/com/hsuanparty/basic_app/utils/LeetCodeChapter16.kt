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

        Log.d(TAG, "result = ${multiply("123", "456")}")

        val str = "abcde"
        val chars = str.toCharArray()
        chars.sort()
        val sort = chars.joinToString("")

        val list = ArrayList<IntArray>()

        val result = Array<IntArray>(list.size) { IntArray(2) }
        list.toArray(result)
    }

    fun multiply(num1: String, num2: String): String {
        val len1 = num1.length
        val len2 = num2.length
        val mul = IntArray(len1 + len2)

        for (i in (len1-1) downTo 0) {
            for (j in (len2-1) downTo 0) {
                val a = num1[i] - '0'
                val b = num2[j] - '0'

                val sum = a * b + mul[i + j + 1]

                mul[i + j + 1] += sum % 10
                mul[i + j] += sum / 10
            }
        }

        val builder = StringBuilder()

        if (mul[0] != 0) {
            builder.append(mul[0])
        }
        for (i in 1 until mul.size) {
            builder.append(mul[i])
        }

        return builder.toString()
    }

    companion object {
        private const val TAG = "leetcode"
    }
}