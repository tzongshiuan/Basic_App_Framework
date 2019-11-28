package com.hsuanparty.basic_app.utils.implement_data_structure

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/11/28
 * Description:
 */
class Q1_StackByArray(size: Int) {
    val size = size
    val arr = IntArray(size)
    var top = -1

    fun push(value: Int) {
        if (isFull()) {
            println("Stack is full!")
            return
        }

        top++
        arr[top] = value
        println("Push value: $value")
    }

    fun pop(): Int {
        if (isEmpty()) {
            println("Stack is empty!")
            return -1
        }

        val result = arr[top]
        top--
        return result
    }

    fun peek(): Int {
        if (isEmpty()) {
            println("Stack is empty!")
            return -1
        }

        return arr[top]
    }

    fun isEmpty(): Boolean {
        return top == -1
    }

    fun isFull(): Boolean {
        return top == size - 1
    }
}