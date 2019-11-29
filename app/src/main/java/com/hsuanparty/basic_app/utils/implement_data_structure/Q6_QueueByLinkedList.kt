package com.hsuanparty.basic_app.utils.implement_data_structure

import java.util.*

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/11/28
 * Description:
 */
class Q6_QueueByLinkedList {

    inner class Node(data: Int) {
        var data = data
        var next: Node? = null
    }

    var front: Node? = null
    var rear: Node? = null
    var currentSize = 0

    fun add(value: Int) {
        val node = Node(value)

        if (rear == null) {
            rear = node
            front = node
        } else {
            rear?.next = node
            rear = rear?.next
        }

        currentSize++
    }

    fun remove(): Int? {
        if (isEmpty()) {
            throw EmptyStackException()
        }

        val value = front?.data
        front = front?.next
        currentSize--

        return value
    }

    fun peek(): Int? {
        if (isEmpty()) {
            throw EmptyStackException()
        }

        return front?.data
    }

    fun isEmpty(): Boolean {
        return currentSize == 0
    }
}