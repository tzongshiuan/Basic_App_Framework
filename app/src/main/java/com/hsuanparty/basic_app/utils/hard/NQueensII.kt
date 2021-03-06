package com.hsuanparty.basic_app.utils.hard

import java.util.*
import kotlin.collections.ArrayList

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/12/11
 * Description:
 */
class NQueensII {
    fun totalNQueens(n: Int): Int {
        val board = Array(n) { CharArray(n) { '.' } }
        return dfs(board, 0)
    }

//    fun solveNQueens(n: Int): List<List<String>> {
//        val board = Array(n) { CharArray(n) { '.' } }
//
//        val result = ArrayList<List<String>>()
//        dfs(board, 0, result)
//        return result
//    }

    private fun dfs(board: Array<CharArray>, col: Int): Int {
        if (col == board.size) {
            return 1
        }

        var count = 0

        for (i in board.indices) {
            if (validate(board, i, col)) {
                board[i][col] = 'Q'
                count += dfs(board, col + 1)
                board[i][col] = '.'
            }
        }

        return count
    }

    private fun validate(board: Array<CharArray>, r: Int, c: Int): Boolean {
        for (i in board.indices) {
            for (j in 0 until c) {
                if (board[i][j] == 'Q' && (r + j == c + i || r + c == i + j || r == i)) {
                    return false
                }
            }
        }

        return true
    }

//    private fun construct(board: Array<CharArray>): List<String> {
//        val result = LinkedList<String>()
//        for (i in board.indices) {
//            result.add(String(board[i]))
//        }
//        return result
//    }
}