package com.hsuanparty.basic_app.utils.implement_data_structure

import java.util.*

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/11/28
 * Description:
 */
class Q5_QueueByArray(size: Int) {
    private val capacity = size
    private val array = IntArray(size)
    var front = 0
    var rear = 0
    var currentSize = 0

    fun add(value: Int) {
        if (isFull()) {
            throw StackOverflowError()
        }

        array[rear] = value
        rear++
        currentSize++

        if (rear == capacity) {
            rear = 0
        }
    }

    fun remove(): Int {
        if (isEmpty()) {
            throw EmptyStackException()
        }

        val value = array[front]
        front++
        currentSize--

        if (front == capacity) {
            front = 0
        }

        return value
    }

    fun isFull(): Boolean {
        return currentSize == capacity
    }

    fun isEmpty(): Boolean {
        return currentSize == 0
    }
}