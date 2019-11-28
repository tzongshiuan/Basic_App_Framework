package com.hsuanparty.basic_app.utils.implement_data_structure

import java.util.*

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/11/28
 * Description:
 */
class Q3_StackByQueue {
    var queue1 = LinkedList<Int>()
    var queue2 = LinkedList<Int>()

    fun push(value: Int) {
        queue1.add(value)
        while (!queue2.isEmpty()) {
            queue1.add(queue2.remove())
        }

        val tmp = queue1
        queue1 = queue2
        queue2 = tmp
    }

    fun pop(): Int {
        if (isEmpty()) {
            throw EmptyStackException()
        }

        return queue2.remove()
    }

    fun peek(): Int {
        if (isEmpty()) {
            throw EmptyStackException()
        }

        return queue2.peek()
    }

    fun isEmpty(): Boolean {
        return queue2.isEmpty()
    }
}