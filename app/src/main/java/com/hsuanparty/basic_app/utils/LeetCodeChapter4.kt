package com.hsuanparty.basic_app.utils

import android.util.Log
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

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
//        val node1 = TreeNode(1)
//        val node2 = TreeNode(2)
//        val node3 = TreeNode(3)
//        val node4 = TreeNode(4)
//        val node5 = TreeNode(5)
//        val node6 = TreeNode(6)
//        val node7 = TreeNode(7)
//        node4.left = node2
//        node4.right = node6
//        node2.left = node1
//        node2.right = node3
//        node6.left = node5
//        node6.right = node7
//        node2.parent = node4
//        node6.parent = node4
//        node1.parent = node2
//        node3.parent = node2
//        node5.parent = node6
//        node7.parent = node6
//        Log.d(TAG, "The next node of node6 is node${inOrderSucc(node6)?.data}")
//        Log.d(TAG, "The next node of node3 is node${inOrderSucc(node3)?.data}")
//        Log.d(TAG, "The next node of node7 is node${inOrderSucc(node7)?.data}")

        // 4.7 Build Order
//        val projects = arrayOf("a", "b", "c", "d", "e", "f")
//        val dependencies = arrayOf(
//                arrayOf("a", "d"), arrayOf("f", "b"), arrayOf("b", "d"), arrayOf("f", "a"), arrayOf("d", "c")
//            )
//        val stackOrder = findBuildOrder(projects, dependencies)
//        val builder = StringBuilder()
//        while (stackOrder?.isEmpty() == false) {
//            builder.append("${stackOrder.pop().name} ")
//        }
//        Log.d(TAG, "Stack order is: $builder")

        // 4.8 Find Common Ancestor
//        val node1 = TreeNode(1)
//        val node2 = TreeNode(2)
//        val node3 = TreeNode(3)
//        val node4 = TreeNode(4)
//        val node5 = TreeNode(5)
//        val node6 = TreeNode(6)
//        val node7 = TreeNode(7)
//        node4.left = node2
//        node4.right = node6
//        node2.left = node1
//        node2.right = node3
//        node6.left = node5
//        node6.right = node7
//        node2.parent = node4
//        node6.parent = node4
//        node1.parent = node2
//        node3.parent = node2
//        node5.parent = node6
//        node7.parent = node6
//        val commonAncestor = findCommonAncestor(node4, node1, node2)
//        Log.d(TAG, "Common Ancestor between node1 and node2 is: node${commonAncestor?.data}")
//        val commonAncestor2 = findCommonAncestor(node4, node7, node6)
//        Log.d(TAG, "Common Ancestor between node7 and node6 is: node${commonAncestor2?.data}")

        // 4.9 BST Sequences
//        val node1 = TreeNode(1)
//        val node2 = TreeNode(2)
//        val node3 = TreeNode(3)
//        val node4 = TreeNode(4)
//        val node5 = TreeNode(5)
//        val node6 = TreeNode(6)
//        val node7 = TreeNode(7)
//        node4.left = node2
//        node4.right = node6
//        node2.left = node1
//        node2.right = node3
//        node6.left = node5
//        node6.right = node7
//        node2.parent = node4
//        node6.parent = node4
//        node1.parent = node2
//        node3.parent = node2
//        node5.parent = node6
//        node7.parent = node6
//        val sequences = allSequences(node4)
//        for (list in sequences) {
//            val builder = StringBuilder()
//            for (i in list) {
//                builder.append("$i ")
//            }
//            Log.d(TAG, "List: $builder")
//        }

        // 4.10 Check Subtree
//        val node1 = TreeNode(1)
//        val node2 = TreeNode(2)
//        val node3 = TreeNode(3)
//        val node4 = TreeNode(4)
//        val node5 = TreeNode(5)
//        val node6 = TreeNode(6)
//        val node7 = TreeNode(7)
//        node4.left = node2
//        node4.right = node6
//        node2.left = node1
//        node2.right = node3
//        node6.left = node5
//        node6.right = node7
//        node2.parent = node4
//        node6.parent = node4
//        node1.parent = node2
//        node3.parent = node2
//        node5.parent = node6
//        node7.parent = node6
//        Log.d(TAG, "Is t1 has subtree t2: ${containsTree(node4, node6)}")
//        Log.d(TAG, "Is t1 has subtree t2: ${containsTree(node2, node6)}")

        // 4.11 Random Node
//        val node = RandomTreeNode(1)
//        node.insertInOrder(2)
//        node.insertInOrder(3)
//        node.insertInOrder(4)
//        node.insertInOrder(5)
//        node.insertInOrder(6)
//        node.insertInOrder(7)
//
//        Log.d(TAG, "Random tree node between 1-7: node${node.getRandomNode()?.data}")
//        Log.d(TAG, "Random tree node between 1-7: node${node.getRandomNode()?.data}")
//        Log.d(TAG, "Random tree node between 1-7: node${node.getRandomNode()?.data}")
//        Log.d(TAG, "Random tree node between 1-7: node${node.getRandomNode()?.data}")
//        Log.d(TAG, "Random tree node between 1-7: node${node.getRandomNode()?.data}")

        // 4.12 Paths with Sum
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

        Log.d(TAG, "Paths count which have sum 3 is ${countPathSum(node4, 3)}")    // 2
        Log.d(TAG, "Paths count which have sum 5 is ${countPathSum(node4, 5)}")    // 1
        Log.d(TAG, "Paths count which have sum 13 is ${countPathSum(node4, 13)}")  // 1
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
    private fun leftMostChild(node: TreeNode?): TreeNode? {
        if (node == null) {
            return null
        }

        var n = node
        while (n?.left != null) {
            n = n.left!!
        }
        return n
    }
    private fun inOrderSucc(node: TreeNode?): TreeNode? {
        if (node == null) {
            return null
        }

        if (node.right != null) {
            return leftMostChild(node.right)
        } else {
            var current = node
            var parent = current.parent
            while (parent != null && parent.left != current) {
                current = parent
                parent = parent.parent
            }
            return parent
        }
    }

    // 4.7
    class ProjectGraph {
        val nodes = ArrayList<Project>()
        val map = HashMap<String, Project>()

        fun createNode(name: String) {
            val node = Project(name)
            nodes.add(node)
            map[name] = node
        }

        fun addEdge(startName: String, endName: String) {
            if (map.containsKey(startName) && map.containsKey(endName)) {
                val start = map[startName]!!
                val end = map[endName]!!
                start.addNeighbor(end)
            }
        }
    }
    class Project(name: String) {
        var name = name
        var state = State.Unvisited
        val children = ArrayList<Project>()

        fun addNeighbor(node: Project) {
            children.add(node)
        }
    }
    private fun buildGraph(projects: Array<String>, dependencies: Array<Array<String>>): ProjectGraph {
        val graph = ProjectGraph()

        for (project in projects) {
            graph.createNode(project)
        }

        for (dependency in dependencies) {
            graph.addEdge(dependency[0], dependency[1])
        }

        return graph
    }
    private fun doDFS(project: Project, stack: Stack<Project>): Boolean {
        if (project.state == State.Visiting) {
            // circle
            return false
        }

        if (project.state == State.Unvisited) {
            project.state = State.Visiting

            for (child in project.children) {
                if (!doDFS(child, stack)) {
                    return false
                }
            }

            project.state = State.Visited
            stack.push(project)
        }

        return true
    }
    private fun orderProjects(projects: ArrayList<Project>): Stack<Project>? {
        val stack = Stack<Project>()

        for (project in projects) {
            if (project.state == State.Unvisited) {
                if (!doDFS(project, stack)) {
                    return null
                }
            }
        }
        return stack
    }
    private fun findBuildOrder(projects: Array<String>, dependencies: Array<Array<String>>): Stack<Project>? {
        val graph = buildGraph(projects, dependencies)
        return orderProjects(graph.nodes)
    }

    // 4.8
    private fun depth(node: TreeNode?): Int {
        var depth = 0
        var n = node
        while (n?.parent != null) {
            n = n.parent
            depth++
        }
        return depth
    }
    private fun goUpLevel(node: TreeNode?, delta: Int): TreeNode? {
        var n = node
        var d = delta

        while (n != null) {
            n = n.parent
            d--
        }
        return n
    }

    private fun covers(root: TreeNode?, p: TreeNode?): Boolean {
        if (root == null) {
            return false
        } else if (root == p) {
            return true
        }
        return covers(root.left, p) || covers(root.right, p)
    }
    private fun getSibling(node: TreeNode?): TreeNode? {
        if (node == null || node.parent == null) {
            return null
        }

        val parent = node.parent

        return if (parent?.left == node) parent.right else parent?.left
    }

    private fun findCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        // solution 1, is node has parent
//        if (root == null || p == null || q == null) {
//            return null
//        }
//        val delta = depth(p) - depth(q)
//        var shallow: TreeNode? = if (delta < 0) p else q
//        var deep: TreeNode? = if (delta < 0) q else p
//        deep = goUpLevel(deep, Math.abs(delta))
//
//        while (shallow != deep && shallow != null && deep != null) {
//            shallow = shallow.parent
//            deep = deep.parent
//        }
//        return shallow

        // solution 2, improved from (1)
//        if (!covers(root, p) || !covers(root, q)) {
//            return null
//        } else if (covers(p, q)) {
//            return p
//        } else if (covers(q, p)) {
//            return q
//        }
//
//        var sibling = getSibling(p)
//        var parent = p?.parent
//
//        while(!covers(sibling, q)) {
//            sibling = getSibling(parent)
//            parent = parent?.parent
//        }
//        return parent

        // solution 3, no parent, find node covers both p and q
        if (root == null) {
            return null
        }
        if (p == q) {
            return p
        }

        var left = findCommonAncestor(root.left, p, q)
        if (left != null && left != p && left != q) {
            return left
        }

        var right = findCommonAncestor(root.right, p, q)
        if (right != null && right != p && right != q) {
            return right
        }

        if (left != null && right != null) {
            return root  // find common ancestor
        } else if (root == p || root == q) {
            return root  // 向上傳遞
        } else {
            return if (left == null) right else left
        }
    }

    // 4.9
    // weave function
    private fun weaveLists(left: LinkedList<Int>, right: LinkedList<Int>,
                           weaved: ArrayList<LinkedList<Int>>, prefix: LinkedList<Int>) {
        if (left.size == 0 || right.size == 0) {
            val result = prefix.clone() as LinkedList<Int>
            result.addAll(left)
            result.addAll(right)
            weaved.add(result)
            return
        }

        val headFirst = left.removeFirst()
        prefix.addLast(headFirst)
        weaveLists(left, right, weaved, prefix)
        prefix.removeLast()
        left.addFirst(headFirst)

        val headSecond = right.removeFirst()
        prefix.addLast(headSecond)
        weaveLists(left, right, weaved, prefix)
        prefix.removeLast()
        right.addFirst(headSecond)
    }
    // weave array of subtrees of both side
    private fun allSequences(root: TreeNode?): ArrayList<LinkedList<Int>> {
        val result = ArrayList<LinkedList<Int>>()

        if (root == null) {
            result.add(LinkedList<Int>())
            return result
        }

        val prefix = LinkedList<Int>()
        prefix.add(root.data)

        val leftSeq = allSequences(root.left)
        val rightSeq = allSequences(root.right)

        for (left in leftSeq) {
            for (right in rightSeq) {
                val weaved = ArrayList<LinkedList<Int>>()
                weaveLists(left, right, weaved, prefix)
                result.addAll(weaved)
            }
        }

        return result
    }

    // 4.10
    private fun getOrderString(root: TreeNode?, builder: StringBuilder) {
        if (root == null) {
            builder.append("X")
            return
        }

        builder.append(root.data)
        getOrderString(root.left, builder)
        getOrderString(root.right, builder)
    }
    private fun matchTree(t1: TreeNode?, t2: TreeNode?): Boolean {
        if (t1 == null && t2 == null) {
            return true
        } else if (t1 == null || t2 == null) {
            return false
        } else if (t1.data != t2.data) {
            return false
        }

        return matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right)
    }
    private fun subTree(t1: TreeNode?, t2: TreeNode?): Boolean {
        if (t1 == null) {
            return false
        }

        if (t1.data == t2?.data && matchTree(t1, t2)) {
            return true
        }

        return subTree(t1.left, t2) || subTree(t1.right, t2)
    }
    private fun containsTree(t1: TreeNode?, t2: TreeNode?): Boolean {
        // solution 1, Tag null node + pre-order
//        val string1 = StringBuilder()
//        val string2 = StringBuilder()
//
//        getOrderString(t1, string1)
//        getOrderString(t2, string2)
//
//        return string1.contains(string2.toString())

        // solution 2, find node in t1 and compare
        if (t2 == null) {
            return true
        }
        return subTree(t1, t2)
    }

    // 4.11
    class RandomTreeNode(value: Int) {
        var data = value
        var size = 1
        var left: RandomTreeNode? = null
        var right: RandomTreeNode? = null

        fun getRandomNode(): RandomTreeNode? {
            val leftSize = left?.size ?: 0
            val random = Random()
            val index = random.nextInt(size)

            if (index < leftSize) {
                return left?.getRandomNode()
            } else if (index == leftSize) {
                return this
            } else {
                return right?.getRandomNode()
            }
        }

        fun insertInOrder(d: Int) {
            if (d <= data) {
                if (left == null) {
                    left = RandomTreeNode(d)
                } else {
                    left?.insertInOrder(d)
                }
            } else {
                if (right == null) {
                    right = RandomTreeNode(d)
                } else {
                    right?.insertInOrder(d)
                }
            }
            size++
        }

        fun find(d: Int): RandomTreeNode? {
            if (d == data) {
                return this
            } else if (d <= data) {
                return left?.find(d)
            } else if (d > data) {
                return right?.find(d)
            }
            return null
        }
    }

    // 4.12
    private fun countPathSumFromNode(root: TreeNode?, targetSum: Int, currentSum: Int): Int {
        if (root == null) {
            return 0
        }

        var pathCount = 0
        val sum = currentSum + root.data

        if (sum == targetSum) {
            pathCount++
        }

        val pathsLeft = countPathSumFromNode(root.left, targetSum, sum)
        val pathsRight = countPathSumFromNode(root.right, targetSum, sum)
        return pathCount + pathsLeft + pathsRight
    }
    private fun incrementMap(pathMap: HashMap<Int, Int>, key: Int, delta: Int) {
        val count = pathMap.getOrDefault(key, 0) + delta

        if (count == 0) {
            pathMap.remove(key)
        } else {
            pathMap.put(key, count)
        }
    }
    private fun countPathSum(root: TreeNode?, targetSum: Int, runningSum: Int, pathMap: HashMap<Int, Int>): Int {
        if (root == null) {
            return 0
        }

        var currentSum = runningSum + root.data
        var sum = currentSum - targetSum
        var pathCount = pathMap.getOrDefault(sum, 0)

        if (currentSum == targetSum) {
            pathCount++
        }

        incrementMap(pathMap, currentSum, 1)
        pathCount += countPathSum(root.left, targetSum, currentSum, pathMap)
        pathCount += countPathSum(root.right, targetSum, currentSum, pathMap)
        incrementMap(pathMap, currentSum, -1)

        return pathCount
    }
    private fun countPathSum(root: TreeNode?, targetSum: Int): Int {
        // solution 1, Brute Force
//        if (root == null) {
//            return 0
//        }
//
//        val pathsFromRoot = countPathSumFromNode(root, targetSum, 0)
//        val pathsLeft = countPathSum(root.left, targetSum)
//        val pathsRight = countPathSum(root.right, targetSum)
//        return pathsFromRoot + pathsLeft + pathsRight

        // solution 2, record running sum
        return countPathSum(root, targetSum, 0, HashMap<Int, Int>())
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