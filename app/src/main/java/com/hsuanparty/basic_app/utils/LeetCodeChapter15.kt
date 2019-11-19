package com.hsuanparty.basic_app.utils

import android.util.Log
import java.lang.Exception
import java.util.concurrent.Semaphore
import java.util.concurrent.locks.ReentrantLock

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/11/18
 * Description:
 */
class LeetCodeChapter15 {

    fun start() {
        Log.d(TAG, "start()")

        // 15.3 Dining Philosophers
//        val left = Chopstick()
//        val right = Chopstick()
//        val p1 = Philosopher(left, right)
//        p1.start()

        // 15.5 Call in Order
//        val foo = Foo()
//        foo.first()
//        foo.second()
//        foo.third()

        // 15.7 FizzBuzz
        val thread1 = FizzBuzzThread(div3 = true, div5 = true, max = 100, toPrint = "FizzBuzz")
        val thread2 = FizzBuzzThread(div3 = true, div5 = false, max = 100, toPrint = "Fizz")
        val thread3 = FizzBuzzThread(div3 = false, div5 = true, max = 100, toPrint = "Buzz")
        val thread4 = NumberThread(div3 = false, div5 = false, max = 100)
        thread1.start()
        thread2.start()
        thread3.start()
        thread4.start()
    }

    // 15.3
    class Chopstick {
        private val lock = ReentrantLock()

        fun pickUp(): Boolean {
            return lock.tryLock()
        }

        fun putDown(): Boolean {
            return lock.tryLock()
        }
    }
    class Philosopher(left: Chopstick, right: Chopstick): Thread() {
        val bites = 10
        val left = left
        val right = right

        fun eat() {
            if (pickUp()) {
                putDown()
            }
        }

        fun pickUp(): Boolean {
            if (!left.pickUp()) {
                return false
            }

            if (!right.pickUp()) {
                left.putDown()
                return false
            }

            return true
        }

        fun putDown() {
            left.putDown()
            right.putDown()
        }

        override fun run() {
            for (i in 0 until bites) {
                eat()
            }
        }
    }

    // 15.5
    class Foo {
        val sem1 = Semaphore(1)
        val sem2 = Semaphore(1)

        init {
            sem1.acquire()
            sem2.acquire()
        }

        fun first() {
            try {
                // TODO ...
                sem1.release()
            } catch (e: Exception) {
            }
        }

        fun second() {
            try {
                sem1.acquire()
                sem1.release()
                // TODO ...
                sem2.release()
            } catch (e: Exception) {
            }
        }

        fun third() {
            try {
                sem2.acquire()
                sem2.release()
                // TODO ...
            } catch (e: Exception) {
            }
        }
    }

    // 15.7
    class NumberThread(div3: Boolean, div5: Boolean, max: Int): FizzBuzzThread(div3, div5, max, "") {
        override fun print() {
            Log.d(TAG, "$current")
        }
    }
    open class FizzBuzzThread(div3: Boolean, div5: Boolean, max: Int, toPrint: String): Thread() {
        val div3 = div3
        val div5 = div5
        val max = max
        val toPrint = toPrint

        companion object {
            val lock = Any()
            var current = 1
        }

        open fun print() {
            Log.d(TAG, toPrint)
        }

        override fun run() {
            while (true) {
                synchronized(lock) {
                    if (current > max) {
                        return
                    }

                    if ((current % 3 == 0) == div3 &&
                        (current % 5 == 0) == div5) {
                        print()
                        current++
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "leetcode"
    }
}