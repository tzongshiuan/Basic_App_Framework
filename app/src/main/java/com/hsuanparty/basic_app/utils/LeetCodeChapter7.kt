package com.hsuanparty.basic_app.utils

import android.util.Log
import java.util.*

class LeetCodeChapter7 {

    fun start() {
        // 7.1 Deck of Cards
//        val numHands = 5
//        val automator = BlackJackGameAutomator(numHands)
//        automator.initializeDeck()
//        var success = automator.dealInitial()
//        if (!success) {
//            Log.d(TAG, "Error. Out of cards.")
//        } else {
//            Log.d(TAG,"-- Initial --")
//            automator.printHandsAndScore()
//            val blackjacks = automator.getBlackJacks()
//            if (blackjacks.size > 0) {
//                Log.d(TAG,"Blackjack at ")
//                for (i in blackjacks) {
//                    Log.d(TAG,"$i, ")
//                }
//            } else {
//                success = automator.playAllHands()
//                if (!success) {
//                    Log.d(TAG,"Error. Out of cards.")
//                } else {
//                    Log.d(TAG,"\n-- Completed Game --")
//                    automator.printHandsAndScore()
//                    val winners = automator.getWinners()
//                    if (winners.size > 0) {
//                        Log.d(TAG,"Winners: ")
//                        for (i in winners) {
//                            Log.d(TAG,"$i, ")
//                        }
//                    } else {
//                        Log.d(TAG,"Draw. All players have busted.")
//                    }
//                }
//            }
//        }

        // 7.2 Call Center

        // 7.3 Jukebox
    }

    // 7.1
    enum class Suit(v: Int) {
        Club(0),
        Diamond(1),
        Heart(2),
        Spade(3);

        private var value: Int = v
        fun getValue(): Int {
            return value
        }

        companion object {
            fun getSuitFromValue(value: Int): Suit? {
                when (value) {
                    0 -> return Club
                    1 -> return Diamond
                    2 -> return Heart
                    3 -> return Spade
                    else -> return null
                }
            }
        }
    }

    abstract class Card {
        var available = true
        var faceValue = 0
        var suit: Suit

        constructor(v: Int, s: Suit) {
            this.faceValue = v
            this.suit = s
        }

        abstract fun value(): Int

        fun print() {
            val faceValues =
                arrayOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
            Log.d(TAG, faceValues[faceValue - 1])
            when (suit) {
                Suit.Club -> Log.d(TAG, "c")
                Suit.Heart -> Log.d(TAG, "h")
                Suit.Diamond -> Log.d(TAG, "d")
                Suit.Spade -> Log.d(TAG, "s")
            }
            Log.d(TAG, " ")
        }
    }

    open class Hand<T: Card> {
        val cards = ArrayList<T>()

        open fun score(): Int {
            var score = 0
            for (card in cards) {
                score += card.value()
            }
            return score
        }

        fun addCard(card: T) {
            cards.add(card)
        }

        fun print() {
            for (card in cards) {
                card.print()
            }
        }
    }

    class Deck<T: Card> {
        var cards = ArrayList<T>()
        var dealIndex = 0

        fun setDeckCards(deckCards: ArrayList<T>) {
            cards = deckCards
        }

        fun shuffle() {
            val random = Random()
            for (i in cards.indices) {
                val j = random.nextInt(cards.size - i)
                val card1 = cards[i]
                val card2 = cards[j]
                cards[i] = card2
                cards[j] = card1
            }
        }

        fun remainingCards(): Int {
            return cards.size - dealIndex
        }

        fun dealHand(num: Int): Array<T>? {
            if (remainingCards() < num) {
                return null
            }

            val hand = arrayOfNulls<Card>(num) as Array<T>
            var count = 0
            while (count < num) {
                val card = dealCard()
                hand[count] = card!!
                count++
            }

            return hand
        }

        fun dealCard(): T? {
            if (remainingCards() == 0) {
                return null
            }

            val card = cards[dealIndex]
            card.available = false
            dealIndex++

            return card
        }

        fun print() {
            for (card in cards) {
                card.print()
            }
        }
    }

    class BlackJackCard(c: Int, s: Suit): Card(c, s) {
        override fun value(): Int {
            if (isAce()) {
                return 1
            } else if (isFaceCard()) {
                return 10
            } else {
                return faceValue
            }
        }

        fun minValue(): Int {
            if (isAce()) {
                return 1
            } else {
                return value()
            }
        }

        fun maxValue(): Int {
            if (isAce()) {
                return 11
            } else {
                return value()
            }
        }

        fun isAce(): Boolean {
            return faceValue == 1
        }

        fun isFaceCard(): Boolean {
            return faceValue in 11..13
        }
    }

    class BlackJackHand: Hand<BlackJackCard>() {
        fun addCardToScoreList(card: BlackJackCard, scores: ArrayList<Int>) {
            if (scores.size == 0) {
                scores.add(0)
            }

            for (i in scores.indices) {
                val score = scores[i]
                scores[i] = score + card.minValue()
                if (card.minValue() != card.maxValue()) {
                    scores.add(score + card.maxValue())
                }
            }
        }

        fun possibleScores(): ArrayList<Int> {
            val scores = ArrayList<Int>()
            if (cards.size == 0) {
                return scores
            }

            for (card in cards) {
                addCardToScoreList(card, scores)
            }
            return scores
        }

        override fun score(): Int {
            val scores = possibleScores()
            var maxUnder = Int.MIN_VALUE
            var minOver = Int.MAX_VALUE

            for (score in scores) {
                if (score in 22 until minOver) {
                    minOver = score
                } else if (score in (maxUnder + 1)..21) {
                    maxUnder = score
                }
            }

            return if (maxUnder == Int.MIN_VALUE) minOver else maxUnder
        }

        fun busted(): Boolean {
            return score() > 21
        }

        fun is21(): Boolean {
            return score() == 21
        }

        fun isBlackJack(): Boolean {
            if (cards.size != 2) {
                return false
            }

            val first = cards[0]
            val second = cards[1]
            return (first.isAce() && second.isFaceCard()) || (first.isFaceCard() && second.isAce())
        }
    }

    class BlackJackGameAutomator(numPlayers: Int) {
        var deck = Deck<BlackJackCard>()
        var hands: Array<BlackJackHand?> = arrayOfNulls(numPlayers)
        private val HIT_UNTIL = 16

        init {
            for (i in 0 until numPlayers) {
                hands[i] = BlackJackHand()
            }
        }

        fun dealInitial(): Boolean {
            for (hand in hands) {
                val card1 = deck.dealCard()
                val card2 = deck.dealCard()

                if (card1 == null || card2 == null) {
                    return false
                }

                hand?.addCard(card1)
                hand?.addCard(card2)
            }

            return true
        }

        fun getBlackJacks(): ArrayList<Int> {
            val winners = ArrayList<Int>()
            for (i in hands.indices) {
                if (hands[i]?.isBlackJack()!!) {
                    winners.add(i)
                }
            }
            return winners
        }

        fun playHand(i: Int): Boolean {
            val hand = hands[i]
            return playHand(hand!!)
        }

        fun playHand(hand: BlackJackHand): Boolean {
            while (hand.score() < HIT_UNTIL) {
                val card = deck.dealCard() ?: return false
                hand.addCard(card)
            }
            return true
        }

        fun playAllHands(): Boolean {
            for (hand in hands) {
                if (!playHand(hand!!)) {
                    return false
                }
            }
            return true
        }

        fun getWinners(): ArrayList<Int> {
            val winners = ArrayList<Int>()
            var winningScore = 0
            for (i in hands.indices) {
                val hand = hands[i]
                if (!hand?.busted()!!) {
                    if (hand?.score()!! > winningScore) {
                        winningScore = hand?.score()
                        winners.clear()
                        winners.add(i)
                    } else if (hand?.score() == winningScore) {
                        winners.add(i)
                    }
                }
            }
            return winners
        }

        fun initializeDeck() {
            val cards = ArrayList<BlackJackCard>()
            for (i in 1..13) {
                for (j in 0..3) {
                    val suit = Suit.getSuitFromValue(j)
                    val card = BlackJackCard(i, suit!!)
                    cards.add(card)
                }
            }

            deck = Deck()
            deck.setDeckCards(cards)
            deck.shuffle()
        }

        fun printHandsAndScore() {
            for (i in hands.indices) {
                print("Hand " + i + " (" + hands[i]?.score() + "): ")
                hands[i]?.print()
                println("")
            }
        }
    }

    // 7.2
    enum class Rank(v: Int) {
        Respondent(0),
        Manager(1),
        Director(2);
        val value = v
    }

    class Caller(name: String, id: Int) {
        var userNmae = name
        var userId = id
    }

    class Call(caller: Caller) {
        var rank = Rank.Respondent
        var caller = caller
        var handler: Employee? = null

        fun disConnect() {}
        fun incrementRank(): Rank { return rank}
    }

    abstract class Employee(handler: CallHandler) {
        val callHandler = handler
        open var rank: Rank = Rank.Respondent
        var currentCall: Call? = null

        fun receivedCall(call: Call) {
            currentCall = call
        }
        fun callCompleted() {}
        fun reAssign() {}
        fun assignNewCall() {}
        fun isFree(): Boolean { return true }
    }

    class Respondent(handler: CallHandler): Employee(handler) {
        override var rank = Rank.Respondent
    }

    class Manager(handler: CallHandler): Employee(handler) {
        override var rank = Rank.Manager
    }

    class Director(handler: CallHandler): Employee(handler) {
        override var rank = Rank.Director
    }

    class CallHandler {
        companion object {
            private const val LEVELS = 3
            private const val NUMBER_RESPONDENTS = 10
            private const val NUMBER_MANAGERS = 4
            private const val NUMBER_DIRECTORS = 2
        }

        val employeeLevels = ArrayList<List<Employee>>(LEVELS)
        val callQueues = ArrayList<List<Call>>(LEVELS)

        init {
            // init employeeLevels
        }

        fun getHandlerForCall(call: Call): Employee? { return null }

        fun dispatchCall(caller: Caller) {}

        fun assignCall(employee: Employee): Boolean { return true }
    }

    // 7.3

    // 7.4
    enum class VehicleSize { Small, Medium, Large }
    abstract class Vehicle {
        val parkingSpots = ArrayList<ParkingSpot>()
        abstract var licensePlate: String
        abstract var spotNeeded: Int
        abstract var size: VehicleSize

        fun parkInSpot(s: ParkingSpot) { parkingSpots.add(s) }

        fun clearSpots() {}

        abstract fun canFitInSpot(spot: ParkingSpot): Boolean
    }

    class ParkingSpot {}

    class ParingLot {
        var levels = arrayOfNulls<Level>(5)
    }

    class Level(flr: Int, numberSpots: Int) {
        val floor = flr
    }

    // 7.5
    class Library {}
    class UserManager {}
    class Display {
        fun displayBook(book: Book?) {}
        fun displayUser(user: User?) {}
    }
    class Book {}
    class User {}
    class onlineReaderSystem {
        val display = Display()
        var activeBook: Book? = null
        set(value) {
            field = value
            display.displayBook(value)
        }
    }

    // 7.11
    abstract class Entry(n: String, p: Directory) {
        protected var name = n
        protected var parent: Directory? = p
        protected var createTime = System.currentTimeMillis()
        protected var updateTime = System.currentTimeMillis()
        protected var accessTime = System.currentTimeMillis()

        fun delete(): Boolean {
            if (parent == null) return false
            return parent!!.deleteEntry(this)
        }

        abstract fun size(): Int

        fun getFullPath(): String {
            if (parent == null) return name
            return parent!!.getFullPath() + "/" + name
        }
    }

    class Directory(n: String, p: Directory): Entry(n, p) {
        private val contents = ArrayList<Entry>()

        override fun size(): Int {
            var size = 0
            for (entry in contents) {
                size += entry.size()
            }
            return size
        }

        fun numberOfFiles(): Int {
            var count = 0
            for (entry in contents) {
                if (entry is Directory) {
                    count += entry.numberOfFiles()
                }
                count++
            }
            return count
        }

        fun deleteEntry(entry: Entry): Boolean { return contents.remove(entry) }

        fun addEntry(entry: Entry) { contents.add(entry) }
    }

    class File(n: String, p: Directory, sz: Int): Entry(n, p) {
        var size = sz
        var content: String? = null

        override fun size(): Int { return size }
    }

    // 7.12
    class Hasher<K, V>(capacity: Int) {
        class LinkedListNode<K, V>(k: K, v: V) {
            var key = k
            var value = v
            var prev: LinkedListNode<K, V>? = null
            var next: LinkedListNode<K, V>? = null
        }

        private val arr = ArrayList<LinkedListNode<K, V>?>()

        init {
            arr.ensureCapacity(capacity)
            for (i in 0 until capacity) {
                arr.add(null)
            }
        }

        fun put(key: K, value: V): V {
            var node = getNodeForKey(key)
            if (node != null) {
                val oldValue = node.value
                node.value = value
                return oldValue
            }

            node = LinkedListNode<K, V>(key, value)
            val index = getIndexForKey(key)
            if (arr[index] != null) {
                node.next = arr[index]
                arr[index]?.prev = node
            }
            arr[index] = node
            return value
        }

        fun remove(key: K): V? {
            val node = getNodeForKey(key)
            if (node == null) {
                return null
            }

            if (node.prev != null) {
                node.prev?.next = node.next
            } else {
                val index = getIndexForKey(key)
                arr[index] = node.next
            }

            if (node.next != null) {
                node.next?.prev = node.prev
            }

            return node.value
        }

        fun get(key: K): V? {
            val node = getNodeForKey(key)
            return if (node == null) null else node.value
        }

        fun getNodeForKey(key: K): LinkedListNode<K, V>? {
            val index = getIndexForKey(key)
            var current = arr[index]
            while (current != null) {
                if (current.key == key) {
                    return current
                }
                current = current.next
            }
            return null
        }

        fun getIndexForKey(key: K): Int {
            return Math.abs(key.hashCode() % arr.size)
        }
    }

    companion object {
        private const val TAG = "leetcode"
    }
}