package com.hsuanparty.basic_app.utils

import android.util.Log
import org.w3c.dom.Node
import java.lang.StringBuilder
import java.util.*

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/11/4
 * Description:
 */
class LeetCodeChapter2 {

    var count = 0

    fun start() {
        // 2.1 Remove Duplicates
//        val test = Node(1).also {
//            it.appendToTail(2)
//            it.appendToTail(5)
//            it.appendToTail(4)
//            it.appendToTail(4)
//            it.appendToTail(3)
//        }
//        deleteDups(test)
//        printResult(test)

        // 2.2 Reture Kth to Last
//        val test = Node(1).also {
//            it.appendToTail(2)
//            it.appendToTail(5)
//            it.appendToTail(4)
//            it.appendToTail(4)
//            it.appendToTail(3)
//        }
//        val node = returnKthToLast(test, 4)
//        Log.d(TAG, "Kth to last, index: 4, data: ${node?.data}")

        // 2.3 Delete Middle Node
//        val test = Node(1).also {
//            it.appendToTail(2)
//            it.appendToTail(5)
//            it.appendToTail(4)
//            it.appendToTail(4)
//            it.appendToTail(3)
//        }
//        deleteMiddleNode(test.next?.next)
//        deleteMiddleNode(test.next?.next)
//        deleteMiddleNode(test.next?.next)
//        printResult(test)

        // 2.4 Partition
//        val test = Node(3).also {
//            it.appendToTail(5)
//            it.appendToTail(8)
//            it.appendToTail(5)
//            it.appendToTail(10)
//            it.appendToTail(2)
//            it.appendToTail(1)
//        }
//        printResult(partition(test, 5)!!)

        // 2.5 Sum Lists
//        val test1 = Node(7).also {
//            it.appendToTail(1)
//            it.appendToTail(6)
//        }
//        val test2 = Node(5).also {
//            it.appendToTail(9)
//            it.appendToTail(3)
//        }
//        printResult(sumLists(test1, test2)!!)
//        printResult(addLists(test1, test2)!!)

        // 2.6 Palindrome
        val test1 = Node(1).also {
            it.appendToTail(2)
            it.appendToTail(3)
            it.appendToTail(2)
            it.appendToTail(1)
        }
        val test2 = Node(5).also {
            it.appendToTail(9)
            it.appendToTail(3)
            it.appendToTail(3)
            it.appendToTail(3)
        }
        Log.d(TAG, "test1 is palindrome: ${isPalinDrome(test1)}")
        Log.d(TAG, "test2 is palindrome: ${isPalinDrome(test2)}")

        // 2.7 Intersection

        // 2.8 Loop Detection
    }

    // 2.1
    private fun deleteDups(head: Node) {
        // solution 1, use SET, O(n)
//        val set = HashSet<Int>()
//        var previous: Node? = null
//        var n: Node? = head
//
//        while (n != null) {
//            if (set.contains(n.data)) {
//                previous?.next = n.next
//            } else {
//                set.add(n.data)
//                previous = n
//            }
//            n = n.next
//        }

        // solution 2, without extra space, O(n^2)
        var current: Node? = head
        while (current?.next != null) {
            var runner: Node? = current

            while (runner?.next != null) {
                if (runner.next?.data == current.data) {
                    current.next = runner.next?.next
                }
                runner = runner.next
            }

            current = current.next
        }
    }

    // 2.2
    var index = 0
    private fun returnKthToLast(head: Node?, k: Int): Node? {
        // solution 1, recursive
////        if (head == null) {
//            return null
//        }
//
//        var node = returnKthToLast(head.next, k)
//        index++
//
//        if (index == k) {
//            return head
//        }
//        return node

        // solution 2, iterative
        var p1: Node? = head
        var p2: Node? = head

        for (i in 0 until k) {
            if (p1 == null) {
                return null
            }
            p1 = p1.next
        }

        while (p1 != null) {
            p1 = p1.next
            p2 = p2?.next
        }

        return p2
    }

    // 2.3
    private fun deleteMiddleNode(node: Node?): Boolean {
        if (node == null) {
            return false
        }

        if (node.next == null) {
            node.isDummy = true
            return false
        }

        node.data = node.next?.data!!
        node.next = node.next?.next
        return true
    }

    // 2.4
    private fun partition(head: Node?, value: Int): Node? {
        // solution 1, handle two list
//        var current: Node? = head
//        var smallStart: Node? = null
//        var smallEnd: Node? = null
//        var bigStart: Node? = null
//        var bigEnd: Node? = null
//
//        while (current != null) {
//            var next = current.next
//            current.next = null
//
//            if (current.data < value) {
//                if (smallStart == null) {
//                    smallStart = current
//                    smallEnd = current
//                } else {
//                    smallEnd?.next = current
//                    smallEnd = smallEnd?.next // current
//                }
//            } else {
//                if (bigStart == null) {
//                    bigStart = current
//                    bigEnd = current
//                } else {
//                    bigEnd?.next = current
//                    bigEnd = current
//                }
//            }
//
//            current = next
//        }
//
//        if (smallStart == null) {
//            return bigStart
//        }
//
//        smallEnd?.next = bigStart
//        return smallStart

        // solution 2, inset to head and tail
        var current: Node? = head
        var hd: Node? = head
        var tail: Node? = head

        while (current != null) {
            var next: Node? = current.next

            if (current.data < value) {
                current.next = hd
                hd = current
            } else {
                tail?.next = current
                tail = current
            }

            current = next
        }

        tail?.next = null

        return hd
    }

    // 2.5
    private fun sumRecursive(list1: Node?, list2: Node?, carry: Int): Node? {
        if (list1 == null && list2 == null && carry == 0) {
            return null
        }

        var result = Node(0)

        var value = carry
        if (list1 != null) {
            value += list1.data
        }

        if (list2 != null) {
            value += list2.data
        }

        result.data = value % 10

        if (list1 != null || list2 != null) {
            result.next = sumRecursive(
                list1?.next, list2?.next, if (value >= 10) 1 else 0
            )
        }

        return result
    }
    private fun sumLists(list1: Node?, list2: Node?): Node? {
        // solution 1, recursive
//        return sumRecursive(list1, list2, 0)

        // soultion 2, iterative
        var result: Node? = null
        var current: Node? = null
        var value = 0
        var carry = 0

        var l1: Node? = list1
        var l2: Node? = list2

        while (l1 != null || l2 != null || carry != 0) {
            value = carry

            if (l1 != null) {
                value += l1.data
                l1 = l1.next
            }
            if (l2 != null) {
                value += l2.data
                l2 = l2.next
            }

            val node = Node(value%10)
            if (value >= 10) carry = 1 else carry = 0

            if (result == null) {
                result = node
                current = result
            } else {
                current?.next = node
                current = current?.next
            }
        }

        return result
    }

    private fun insertBefore(list: Node?, data: Int): Node {
        val node = Node(data)
        if (list != null) {
            node.next = list
        }
        return node
    }
    private fun padList(list: Node?, length: Int): Node? {
        var head: Node? = list
        for (i in 0 until length) {
            head = insertBefore(head, 0)
        }
        return head
    }
    private fun getLength(list: Node?): Int {
        if (list == null) {
            return 0
        }

        var length = 0
        var node = list
        while (node != null) {
            length++
            node = node.next
        }

        return length
    }
    private var carry = 0
    private fun addListsHelper(list1: Node?, list2: Node?): Node? {
        if (list1 == null && list2 == null) {
            return null
        }

        var sum = addListsHelper(list1?.next, list2?.next)

        val value = list1?.data!! + list2?.data!! + carry
        carry = value/10

        sum = insertBefore(sum, value % 10)
        return sum
    }
    private fun addLists(list1: Node?, list2: Node?): Node? {
        val len1 = getLength(list1)
        val len2 = getLength(list2)

        var n1: Node? = null
        var n2: Node? = null
        if (len1 < len2) {
            n1 = padList(list1, len2 - len1)
            n2 = list2
        } else {
            n1 = list1
            n2 = padList(list2, len1 - len2)
        }

        val sum = addListsHelper(n1, n2)

        if (carry == 0) {
            return sum
        } else {
            val result = insertBefore(sum, carry)
            return result
        }
    }

    // 2.6
    private fun isListEqual(list1: Node?, list2: Node?): Boolean {
        var n1 = list1
        var n2 = list2

        while (n1 != null && n2 != null) {
            if (n1.data != n2.data) {
                return false
            }
            n1 = n1.next
            n2 = n2.next
        }
        return true
    }
    private fun reverseList(list: Node?): Node? {
        var head: Node? = null
        var node = list

        while (node != null) {
            val n = Node(node.data)
            n.next = head
            head = n
            node = node.next
        }

        return head
    }

    class Result {
        var node: Node? = null
        var isPalinDrome = false
    }

    private fun isPalinDrome(head: Node?): Boolean {
        // solution 1, reverse and compare
//        val reverse = reverseList(head)
//        return isListEqual(head, reverse)

        // solution 2, iterative through stack
        val stack = Stack<Int>()
        var fast = head
        var slow = head

        while (fast != null && fast.next != null) {
            stack.push(slow?.data)
            slow = slow?.next
            fast = fast.next?.next
        }

        if (fast != null) {
            slow = slow?.next
        }

        while (slow != null) {
            val value = stack.pop()
            if (slow.data != value) {
                return false
            }
            slow = slow.next
        }

        return true

        // solution 3, recursive approach
//        return true
    }

    // 2.7

    // 2.8

    private fun printResult(head: Node) {
        val builder = StringBuilder()
        var n: Node? = head

        builder.append(n?.data)
        while (n?.next != null) {
            n = n.next
            builder.append(" -> ")
            builder.append(n?.data)
        }

        Log.d(TAG, "Result: $builder")
    }

    class Node(d: Int) {
        var data = d
        var next: Node? = null
        var isDummy = false

        fun appendToTail(d: Int) {
            val end = Node(d)
            var n = this
            while (n.next != null) {
                n = n.next!!
            }
            n.next = end
        }
    }

    companion object {
        private const val TAG = "leetcode"
    }
}