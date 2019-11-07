package com.hsuanparty.basic_app.utils

import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/11/6
 * Description:
 */
class LeetCodeChapter4 {

    enum class State { Unvisited, Visited, Visiting }
    class Node(value: Int) {
        var data = value
        var adjacents = ArrayList<Node>()
        var state = State.Unvisited
    }

    class Graph {
        var nodes = ArrayList<Node>()
        var maps: HashMap<Node, Node> = HashMap()
    }

    // iterative
    private fun doDFS(node: Node?) {
        if (node == null) {
            return
        }

        if (node.state == State.Visited) {
            return
        } else {
            node.state = State.Visited
            Log.d(TAG, "node(${node.data}) ")
        }

        for (n in node.adjacents) {
            if (n.state == State.Unvisited) {
                doDFS(n)
            }
        }
    }

    // using queue
    private fun doBFS(node: Node?) {
        val queue = LinkedList<Node>()
        if (node != null) {
            queue.add(node)
        }

        while (!queue.isEmpty()) {
            val n = queue.remove()
            Log.d(TAG, "node(${n.data})")

            for (n in n.adjacents) {
                if (n.state == State.Unvisited) {
                    queue.add(n)
                }
            }
        }
    }

    private fun testSimple() {
        val node1 = Node(1)
        val node2 = Node(2)
        val node3 = Node(3)
        val node4 = Node(4)
        val node5 = Node(5)
        val node6 = Node(6)
        val node7 = Node(7)
        node1.adjacents.add(node2)
        node1.adjacents.add(node3)

        node2.adjacents.add(node4)
        node2.adjacents.add(node5)

        node3.adjacents.add(node6)
        node3.adjacents.add(node7)

        Log.d(TAG, "doDFS()")
        doDFS(node1)
        Log.d(TAG, "doBFS()")
        doBFS(node1)
    }

    fun start() {
        // Simple test basic DFS and BFS algorithm
//        testSimple()

        // 4.1 Route Between Nodes
//        val node1 = Node(1)
//        val node2 = Node(2)
//        val node3 = Node(3)
//        val node4 = Node(4)
//        val node5 = Node(5)
//        val node6 = Node(6)
//        val node7 = Node(7)
//        val node8 = Node(8)
//        val graph = Graph().also {
//            it.nodes.add(node1)
//            it.nodes.add(node2)
//            it.nodes.add(node3)
//            it.nodes.add(node4)
//            it.nodes.add(node5)
//            it.nodes.add(node6)
//            it.nodes.add(node7)
//            it.nodes.add(node8)
//        }
//        node1.adjacents.add(node2)
//        node1.adjacents.add(node3)
//        node2.adjacents.add(node4)
//        node2.adjacents.add(node5)
//        node3.adjacents.add(node6)
//        node3.adjacents.add(node7)
//        Log.d(TAG, "Is have route: ${searchRoute(graph, node1, node2)}")
//        Log.d(TAG, "Is have route: ${searchRoute(graph, node1, node7)}")
//        Log.d(TAG, "Is have route: ${searchRoute(graph, node1, node8)}")

        // 4.2 Minimal Tree (from sorted integer array)
//        val array = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14)
//        val tree = createMinimalBST(array)
//        printBST(tree)

        // 4.3 List of Depths
//        val array = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14)
//        val tree = createMinimalBST(array)
//        val lists = createLevelLinkedList(tree)
//        var index = 0
//        for (list in lists) {
//            val builder = StringBuilder()
//            for (j in list.indices) {
//                builder.append("${list[j].data} ")
//            }
//            Log.d(TAG, "Level $index, list: $builder")
//            index++
//        }

        // 4.4 Check Balanced
//        val array = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14)
//        val tree = createMinimalBST(array)
//        Log.d(TAG, "tree1 is tree balanced: ${isBalanced(tree)}")
//        val tree2 = TreeNode(1)
//        tree2.left = TreeNode(2).also { it.left = TreeNode(3) }
//        Log.d(TAG, "tree2 is tree balanced: ${isBalanced(tree2)}")

        // 4.5 Validate BST
//        val array = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14)
//        val tree = createMinimalBST(array)
//        Log.d(TAG, "tree1 is BST: ${checkBST(tree)}")
//        val tree2 = TreeNode(1)
//        tree2.left = TreeNode(2).also { it.left = TreeNode(3) }
//        Log.d(TAG, "tree2 is BST: ${checkBST(tree2)}")

        // 4.6 Successor
        val node1 = TreeNode(1)
        val node2 = TreeNode(2)
        val node3 = TreeNode(3)
        val node4 = TreeNode(4)
        val node5 = TreeNode(5)
        val node6 = TreeNode(6)
        val node7 = TreeNode(7)
        node4.left = node2
        node4.right = node6
        node2.left = node1
        node2.right = node3
        node6.left = node5
        node6.right = node7
        node2.parent = node4
        node6.parent = node4
        node1.parent = node2
        node3.parent = node2
        node5.parent = node6
        node7.parent = node6
        Log.d(TAG, "The next node of node6 is node${inOrderSucc(node6)?.data}")
        Log.d(TAG, "The next node of node3 is node${inOrderSucc(node3)?.data}")
        Log.d(TAG, "The next node of node7 is node${inOrderSucc(node7)?.data}")
    }

    // 4.1
    private fun searchRouteVisDFS(graph: Graph, start: Node, end: Node): Boolean {
        if (start == end) {
            return true
        }

        start.state = State.Visiting

        var isFind = false
        for (node in start.adjacents) {
            if (node.state == State.Unvisited) {
                node.state = State.Visiting

                isFind = searchRouteVisDFS(graph, node, end)
                if (isFind) {
                    break
                }
            }
        }

        start.state = State.Visited

        return isFind
    }
    private fun searchRoute(graph: Graph, start: Node, end: Node): Boolean {
        if (start == end) {
            return true
        }

        for (n in graph.nodes) {
            n.state = State.Unvisited
        }

        // solution1, via BFS
//        Log.d(TAG, "search route via BFS")
//        val queue = LinkedList<Node>()
//        start.state = State.Visiting
//        queue.add(start)
//
//        while (!queue.isEmpty()) {
//            val current = queue.remove()
//
//            for (next in current.adjacents) {
//                if (next.state == State.Unvisited) {
//                    if (next == end) {
//                        return true
//                    } else {
//                        next.state = State.Visiting
//                        queue.add(next)
//                    }
//                }
//            }
//
//            current.state = State.Visited
//        }
//
//        return false

        // solution2, via DFS
        Log.d(TAG, "search route via DFS")
        return searchRouteVisDFS(graph, start, end)
    }

    // 4.2
    class TreeNode(value: Int) {
        var data = value
        var left: TreeNode? = null
        var right: TreeNode? = null
        var parent: TreeNode? = null
    }
    private fun createMinimalBST(array: IntArray): TreeNode? {
        if (array.isEmpty()) {
            return null
        }

        return createMinimalBST(array, 0, array.size - 1)
    }
    private fun createMinimalBST(array: IntArray, start: Int, end: Int): TreeNode? {
        if (start > end) {
            return null
        }

        val index = (start + end)/2

        val treeNode = TreeNode(array[index])
        treeNode.left = createMinimalBST(array, start, index-1)
        treeNode.right = createMinimalBST(array, index+1, end)

        return treeNode
    }

    // 4.3
    private fun createLevelLinkedList(root: TreeNode?, lists: ArrayList<LinkedList<TreeNode>>, level: Int) {
        if (root == null) {
            return
        }

        var list: LinkedList<TreeNode>
        if (lists.size == level) {
            list = LinkedList()
            lists.add(list)
        } else {
            list = lists[level]
        }
        list.add(root)

        createLevelLinkedList(root.left, lists, level+1)
        createLevelLinkedList(root.right, lists, level+1)
    }
    private fun createLevelLinkedList(root: TreeNode?): ArrayList<LinkedList<TreeNode>> {
        val lists = ArrayList<LinkedList<TreeNode>>()

        // solution 1, pre-order + recursive
//        createLevelLinkedList(root, lists, 0)

        // solution 2, through BST
        if (root == null) {
            return lists
        }

        var queue = LinkedList<TreeNode>()
        queue.add(root)

        while (!queue.isEmpty()) {
            lists.add(queue)
            val parents = queue
            queue = LinkedList<TreeNode>()

            for (parent in parents) {
                if (parent.left != null) {
                    queue.add(parent.left!!)
                }
                if (parent.right != null) {
                    queue.add(parent.right!!)
                }
            }
        }

        return lists
    }

    // 4.4
    private fun getHeight(root: TreeNode?): Int {
        if (root == null) {
            return -1
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1
    }
    private fun checkHeight(root: TreeNode?): Int {
        if (root == null) {
            return -1
        }

        val leftHeight = checkHeight(root.left)
        if (leftHeight == Int.MIN_VALUE) return leftHeight

        val rightHeight = checkHeight(root.right)
        if (rightHeight == Int.MIN_VALUE) return rightHeight

        val diff = Math.abs(leftHeight - rightHeight)
        if (diff > 1) {
            return Int.MIN_VALUE
        } else {
            return Math.max(leftHeight, rightHeight) + 1
        }
    }
    private fun isBalanced(root: TreeNode?): Boolean {
        // solution 1
//        if (root == null) {
//            return true
//        }
//
//        val diffHeight = getHeight(root.left) - getHeight(root.right)
//        if (Math.abs(diffHeight) > 1) {
//            return false
//        } else {
//            return isBalanced(root.left) && isBalanced(root.right)
//        }

        // solution 2, improved from 1
        return checkHeight(root) != Int.MIN_VALUE
    }

    // 4.5
    private fun checkBST(node: TreeNode?, min: Int?, max: Int?): Boolean {
        if (node == null) {
            return true
        }

        if (max != null && node.data > max) {
            return false
        }
        if (min != null && node.data <= min) {
            return false
        }

        if (!checkBST(node.left, min, node.data) || !checkBST(node.right, node.data, max)) {
            return false
        }
        return true
    }
    private var lastPrint: Int? = null
    private fun checkBST(root: TreeNode?): Boolean {
        // solution 1, in-order traverse
//        if (root == null) {
//            return true
//        }
//
//        if (!checkBST(root.left)) {
//            return false
//        }
//
//        if (lastPrint != null && root.data <= lastPrint!!) {
//            return false
//        }
//        lastPrint = root.data
//
//        if (!checkBST(root.right)) {
//            return false
//        }
//        return true

        // solution 2, min/max property
        return checkBST(root, null, null)
    }

    // 4.6
    private fun inOrderSucc(node: TreeNode?): TreeNode? {
    }

    companion object {
        private const val TAG = "leetcode"
    }

    private fun printBST(root: TreeNode?) {
        if (root == null) {
            return
        }

        val builder = StringBuilder()
        printBST(root, builder)

        Log.d(TAG, "BST output: $builder")
    }

    private fun printBST(root: TreeNode, builder: StringBuilder) {
        if (root.left != null) {
            printBST(root.left!!, builder)
        }

        builder.append("${root.data} ")

        if (root.right != null) {
            printBST(root.right!!, builder)
        }
    }
}