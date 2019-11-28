package com.hsuanparty.basic_app.utils.implement_data_structure

import java.util.*

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/11/28
 * Description:
 */
class Q4_SortStackByStack {
    companion object {
        fun sortStack(stack: Stack<Int>): Stack<Int> {
            val result = Stack<Int>()

            while (!stack.isEmpty()) {
                val temp = stack.pop()

                while (!result.isEmpty() && result.peek() > temp) {
                    stack.push(result.pop())
                }

                result.push(temp)
            }

            return result
        }
    }
}