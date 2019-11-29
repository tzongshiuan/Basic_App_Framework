package com.hsuanparty.basic_app.utils.implement_data_structure

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/11/29
 * Description:
 */
class MyArrayList<E> {

    companion object {
        private const val INITIAL_SIZE = 5
    }

    var data = arrayOfNulls<Any?>(INITIAL_SIZE)
    var capacity = INITIAL_SIZE
    var index = 0

    fun add(item: E) {
        if (index == capacity - 1) {
            resize()
        }

        data[index] = item
        index++
    }

    fun get(i: Int): E {
        if (i > index-1) {
            throw Exception("ArrayIndexOutOfBound")
        }

        if (i < 0) {
            throw Exception("Negative Value")
        }

        return data[i] as E
    }

    fun remove(i: Int) {
        if (i > index-1) {
            throw Exception("ArrayIndexOutOfBound")
        }

        if (i < 0) {
            throw Exception("Negative Value")
        }

        for (x in i until capacity) {
            data[x] = data[x+1]
        }

        index--
    }

    private fun resize() {
        this.capacity = this.capacity * 2
        val newData = arrayOfNulls<Any?>(capacity)
        for (i in data.indices) {
            newData[i] = data[i]
        }
        this.data = newData
    }
}