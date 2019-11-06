package com.hsuanparty.basic_app.utils

import android.util.Log
import java.lang.StringBuilder
import java.util.*
import kotlin.NoSuchElementException
import kotlin.math.min

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/11/5
 * Description:
 */
class LeetCodeChapter3 {

    fun start() {
        // basic test
//        val myStack = MyStack<Int>()
//        myStack.push(1)
//        myStack.push(2)
//        myStack.push(3)
//        printMyStack(myStack)
//        val myQueue = MyQueue<Int>()
//        myQueue.add(1)
//        myQueue.add(2)
//        myQueue.add(3)
//        printMyQueue(myQueue)

        // 3.1 Three in One
//        val multiStack = FixedMultiStack(5)
//        multiStack.push(0, 1)
//        multiStack.push(0, 2)
//        multiStack.push(1, 3)
//        multiStack.push(1, 4)
//        multiStack.push(2, 5)
//        multiStack.push(2, 6)
//        printFixedMultiStack(multiStack)

        // 3.2 Stack Min
//        val minStack = StackWithMin()
//        minStack.push(3)
//        minStack.push(1)
//        minStack.push(7)
//        Log.d(TAG, "Minimum value of stack1: ${minStack.min()}")
//        val minStack2 = StackWithMin2()
//        minStack2.push(3)
//        minStack2.push(1)
//        minStack2.push(7)
//        Log.d(TAG, "Minimum value of stack2: ${minStack2.min()}")

        // 3.3 Stack of Plates
//        val setStack = SetOfStacks(2)
//        setStack.push(1)
//        setStack.push(2)
//        setStack.push(3)
//        setStack.push(4)
//        setStack.push(5)
//        Log.d(TAG, "set of stack pop(): ${setStack.pop()}")
//        Log.d(TAG, "set of stack pop(): ${setStack.popAt(0)}")
//        Log.d(TAG, "set of stack pop(): ${setStack.popAt(0)}")
//        Log.d(TAG, "set of stack pop(): ${setStack.popAt(0)}")
//        Log.d(TAG, "set of stack pop(): ${setStack.popAt(0)}")
//        Log.d(TAG, "set of stack pop(): ${setStack.popAt(0)}")

        // 3.4 Queue via Stacks
//        val queue = QueueViaStack<Int>()
//        queue.add(1)
//        queue.add(2)
//        queue.add(3)
//        while (!queue.isEmpty()) {
//            Log.d(TAG, "remove value: ${queue.remove()}")
//        }
//
//        val stack = StackVisQueue<Int>()
//        stack.push(1)
//        stack.push(2)
//        stack.push(3)
//        while (!stack.isEmpty()) {
//            Log.d(TAG, "pop value: ${stack.pop()}")
//        }

        // 3.5 Sort Stack
//        val stack = Stack<Int>()
//        stack.push(1)
//        stack.push(3)
//        stack.push(5)
//        stack.push(7)
//        stack.push(9)
//        sortStack(stack)
//        for (i in stack.indices) {
//            Log.d(TAG, "Stack after sort: ${stack.pop()}")
//        }
////        val stackQuick = sortStackViaQuickSort(stack)
////        for (i in stackQuick.indices) {
////            Log.d(TAG, "Stack after sort via quick sort: ${stackQuick.pop()}")
////        }
//
//        val queue = LinkedList<Int>()
//        queue.add(9)
//        queue.add(7)
//        queue.add(5)
//        queue.add(3)
//        queue.add(1)
//        sortQueue(queue)
//        for (i in queue.indices) {
//            Log.d(TAG, "Queue after sort: ${queue.pop()}")
//        }

        // 3.6 Animal Shelter
        val animalQueue = AnimalQueue()
        animalQueue.enqueue(Dog("1"))
        animalQueue.enqueue(Dog("2"))
        animalQueue.enqueue(Cat("3"))
        animalQueue.enqueue(Dog("4"))
        animalQueue.enqueue(Cat("5"))
        animalQueue.enqueue(Dog("6"))
        animalQueue.enqueue(Cat("7"))
        animalQueue.dequeueAny()
        animalQueue.dequeueAny()
        animalQueue.dequeueAny()
        Log.d(TAG, "The oldest dog name: ${animalQueue.getOrderDogName()}")
        Log.d(TAG, "The oldest cat name: ${animalQueue.getOrderCatName()}")


        // special partition quick sort
//        val array = intArrayOf(9, 8, 7, 6, 5, 4 ,3, 2, 1)
//        quickSort(array, 0, array.size - 1)
//        for (i in array.indices) {
//            Log.d(TAG, "value: ${array[i]}")
//        }
    }


    // 3.1
    class FixedMultiStack {
        val numOfStacks = 3
        var stackCapacity = 0
        var values: IntArray
        var sizes: IntArray

        constructor(stackSize: Int) {
            stackCapacity = stackSize
            values = IntArray(numOfStacks * stackSize)
            sizes = IntArray(numOfStacks)
        }

        fun isFull(stackNum: Int): Boolean {
            return sizes[stackNum] == stackCapacity
        }

        private fun indexOfTop(stackNum: Int): Int {
            val offset = stackNum * stackCapacity
            val index = offset + sizes[stackNum] - 1
            return index
        }

        fun isEmpty(stackNum: Int): Boolean {
            return sizes[stackNum] == 0
        }

        fun push(stackNum: Int, item: Int) {
            if (isFull(stackNum)) {
                throw Exception()
            }

            sizes[stackNum]++
            values[indexOfTop(stackNum)] = item
        }

        fun peek(stackNum: Int): Int {
            if (isEmpty(stackNum)) {
                throw EmptyStackException()
            }

            return values[indexOfTop(stackNum)]
        }

        fun pop(stackNum: Int): Int {
            if (isEmpty(stackNum)) {
                throw EmptyStackException()
            }

            val value = values[indexOfTop(stackNum)]
            values[indexOfTop(stackNum)] = 0
            sizes[stackNum]--

            return value
        }
    }

    // 3.2
    class NodeWithMin(v: Int, m: Int) {
        var value: Int = v
        var min: Int = m
    }
    class StackWithMin: Stack<NodeWithMin>() {
        fun push(value: Int) {
            val min = Math.min(value, min())
            super.push(NodeWithMin(value, min))
        }

        fun min(): Int {
            if (this.isEmpty()) {
                return Int.MAX_VALUE
            } else {
                return this.peek().min
            }
        }
    }
    class StackWithMin2: Stack<Int>() {
        private val s2 = Stack<Int>()

        fun min(): Int {
            if (s2.isEmpty()) {
                return Int.MAX_VALUE
            } else {
                return s2.peek()
            }
        }

        override fun push(value: Int): Int {
            if (value <= min()) {
                s2.push(value)
            }
            return super.push(value)
        }

        override fun pop(): Int {
            val value = super.pop()

            if (value == min()) {
                s2.pop()
            }

            return value
        }
    }

    // 3.3
    class SetOfStacks(capacity: Int) {
        val capacity = capacity
        val stacks = ArrayList<Stack<Int>>()

        fun getLastStack(): Stack<Int>? {
            if (stacks.isEmpty()) {
                return null
            }

            return stacks.last()
        }

        fun push(value: Int) {
            val stack = getLastStack()
            var s: Stack<Int>

            if (stack == null || stack.size == capacity) {
                s = Stack<Int>()
                stacks.add(s)
            } else {
                s = stack
            }

            s.push(value)
        }

        fun pop(): Int {
            val stack = getLastStack() ?: throw EmptyStackException()

            val value = stack.pop()
            if (stack.isEmpty()) {
                stacks.remove(stack)
            }

            return value
        }

        fun popAt(index: Int): Int {
            val stack = stacks[index]
            val value = stack.pop()
            if (stack.isEmpty()) {
                stacks.remove(stack)
            }
            return value
        }

        fun compressStacks() {}
    }

    // 3.4
    class QueueViaStack<T> {
        val stackNewest = Stack<T>()
        val stackOldest = Stack<T>()

        fun size(): Int {
            return stackNewest.size + stackOldest.size
        }

        fun isEmpty(): Boolean {
            return size() == 0
        }

        fun add(value: T) {
            stackNewest.push(value)
        }

        private fun shiftStack() {
            if (stackOldest.isEmpty()) {
                while (!stackNewest.isEmpty()) {
                    stackOldest.push((stackNewest.pop()))
                }
            }
        }

        fun peek(): T {
            shiftStack()
            return stackOldest.peek()
        }

        fun remove(): T {
            shiftStack()
            return stackOldest.pop()
        }
    }
    class StackVisQueue<T> {
        var q1 = LinkedList<T>()
        var q2 = LinkedList<T>()

        fun size(): Int {
            return q1.size + q2.size
        }

        fun isEmpty(): Boolean {
            return size() == 0
        }

        fun push(value: T) {
            q2.add(value)

            while (!q1.isEmpty()) {
                q2.add(q1.remove())
            }

            // swap q1, q2
            val temp = q1
            q1 = q2
            q2 = temp
        }

        fun peek(): T {
            return q1.peek()
        }

        fun pop(): T {
            return q1.remove()
        }
    }

    // 3.5
    private fun sortStack(stack: Stack<Int>) {
        val tmpStack = Stack<Int>()

        while (!stack.isEmpty()) {
            val tmp = stack.pop()

            while (!tmpStack.isEmpty() && tmpStack.peek() > tmp) {
                stack.push(tmpStack.pop())
            }

            tmpStack.push(tmp)
        }

        while (!tmpStack.isEmpty()) {
            stack.push(tmpStack.pop())
        }
    }

    /**
     * Normal quick sort
     */
    private fun partition(array: IntArray, left: Int, right: Int): Int {
        val pivot = array[right]
        var tmp = 0
        var leftIndex = left

        for ( i in left until right) {
            if (array[i] < pivot) {
                tmp = array[leftIndex]
                array[leftIndex] = array[i]
                array[i] = tmp
                leftIndex++
            }
        }

        tmp = array[leftIndex]
        array[leftIndex] = array[right]
        array[right] = tmp

        return leftIndex
    }
    private fun quickSort(array: IntArray, left: Int, right: Int) {
        if (left >= right) {
            return
        }

        val index = partition(array, left, right)
        quickSort(array, left, index - 1)
        quickSort(array, index + 1, right)
    }
    private fun sortStackViaQuickSort(stack: Stack<Int>): Stack<Int> {
        if (stack.isEmpty() || stack.size == 1) {
            return stack
        }

        var s1 = Stack<Int>()
        var s2 = Stack<Int>()
        val pivot = stack.pop()

        while (!stack.isEmpty()) {
            val value = stack.pop()
            if (value < pivot) {
                s1.push(value)
            } else {
                s2.push(value)
            }
        }

        s1 = sortStackViaQuickSort(s1)
        s2 = sortStackViaQuickSort(s2)

        val tmp = Stack<Int>()
        while (!s1.isEmpty()) {
            tmp.push(s1.pop())
        }

        tmp.push(pivot)

        while (!s2.isEmpty()) {
            tmp.push(s2.pop())
        }

        val result = Stack<Int>()
        while (!tmp.isEmpty()) {
            result.push(tmp.pop())
        }

        return result
    }

    private fun findMinIndex(queue: LinkedList<Int>, findLength: Int): Int {
        var minIndex = -1
        var minValue = Int.MAX_VALUE

        for (i in queue.indices) {
            val current = queue.remove()

            if (current <= minValue && i < findLength) {
                minIndex = i
                minValue = current
            }

            queue.add(current)
        }

        return minIndex
    }
    private fun insertIndexToTail(queue: LinkedList<Int>, index: Int) {
        var minValue = 0

        for (i in queue.indices) {
            if (i == index) {
                minValue = queue.remove()
            } else {
                queue.add(queue.remove())
            }
        }

        queue.add(minValue)
    }
    private fun sortQueue(queue: LinkedList<Int>) {
        for (i in queue.indices) {
            val minIndex = findMinIndex(queue, queue.size - i)
            insertIndexToTail(queue, minIndex)
        }
    }

    // 3.6
    abstract class Animal(n: String) {
        var order = 0
        val name = n

        fun isOrderThan(animal: Animal): Boolean {
            return this.order < animal.order
        }
    }
    class Dog(name: String): Animal(name)
    class Cat(name: String): Animal(name)

    class AnimalQueue {
        val dogs = LinkedList<Dog>()
        val cats = LinkedList<Cat>()
        var order = 0

        fun enqueue(animal: Animal) {
            animal.order = order++

            if (animal is Dog) {
                dogs.add(animal)
            } else if (animal is Cat) {
                cats.add(animal)
            }
        }

        fun dequeueCat() {
            cats.remove()
        }

        fun dequeueDog() {
            dogs.remove()
        }

        fun dequeueAny() {
            if (dogs.isEmpty()) {
                return dequeueCat()
            } else if (cats.isEmpty()) {
                return dequeueDog()
            }

            val dog = dogs.peek()
            val cat = cats.peek()

            if (dog.isOrderThan(cat)) {
                dequeueDog()
            } else {
                dequeueCat()
            }
        }

        fun getOrderDogName(): String {
            return dogs.peek().name
        }

        fun getOrderCatName(): String {
            return cats.peek().name
        }
    }





    class MyStack<T> {
        private class StackNode<T> {
            var data: T
            var next: StackNode<T>? = null

            constructor(data: T) {
                this.data = data
            }
        }

        private var top: StackNode<T>? = null

        fun pop(): T {
            if (top == null) throw EmptyStackException()

            val item = top!!.data
            top = top!!.next
            return item
        }

        fun push(item: T) {
            val node = StackNode(item)
            node.next = top
            top = node
        }

        fun peek(): T {
            if (top == null) throw EmptyStackException()
            return top!!.data
        }

        fun isEmpty(): Boolean {
            return top == null
        }
    }

    class MyQueue<T> {
        private class QueueNode<T> {
            var data: T
            var next: QueueNode<T>? = null

            constructor(data: T) {
                this.data = data
            }
        }

        private var first: QueueNode<T>? = null
        private var last: QueueNode<T>? = null

        fun add(item: T) {
            val node = QueueNode(item)
            if (last != null) {
                last?.next = node
            }
            last = node

            if (first == null) {
                first = last
            }
        }

        fun remove(): T {
            if (first == null) throw NoSuchElementException()

            val data = first!!.data
            first = first?.next

            if (first == null) {
                last = null
            }

            return data
        }

        fun peek(): T {
            if (first == null) throw NoSuchElementException()
            return first!!.data
        }

        fun isEmpty(): Boolean {
            return first == null
        }
    }

    private fun printMyStack(stack: MyStack<Int>) {
        val builder = StringBuilder()

        while (!stack.isEmpty()) {
            builder.append("${stack.pop()} ")
        }

        Log.d(TAG, "Stack: $builder")
    }

    private fun printMyQueue(queue: MyQueue<Int>) {
        val builder = StringBuilder()

        while (!queue.isEmpty()) {
            builder.append("${queue.remove()} ")
        }

        Log.d(TAG, "Queue: $builder")
    }

    private fun printFixedMultiStack(stack: FixedMultiStack) {
        val builder = StringBuilder()

        for (i in 0..2) {
            while (!stack.isEmpty(i)) {
                builder.append("${stack.pop(i)} ")
            }
            builder.append("\n")
        }

        Log.d(TAG, "Fixed Multi Stack: $builder")
    }

    companion object {
        private const val TAG = "leetcode"
    }
}