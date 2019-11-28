package com.hsuanparty.basic_app.utils.implement_data_structure

import java.lang.RuntimeException
import java.util.*

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/11/28
 * Description:
 */
class Q2_StackByLinkedList {

    inner class Node(value: Int) {
        var value = value
        var next: Node? = null
    }

    var top: Node? = null

    fun push(value: Int) {
        val node = Node(value)
        node.next = top
        top = node
    }

    fun pop(): Int {
        if (isEmpty()) {
            throw LinkedListEmptyException()
        }

        val value = top!!.value
        top = top?.next

        return value
    }

    fun peek(): Int {
        if (isEmpty()) {
            throw EmptyStackException()
        }

        return top!!.value
    }

    fun isEmpty(): Boolean {
        return top == null
    }

    inner class LinkedListEmptyException: EmptyStackException() {
    }
}