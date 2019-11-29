package com.hsuanparty.basic_app.utils.implement_data_structure

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/11/29
 * Description:
 */
class MyHashMap<K, V> {
    inner class Entry<K, V>(key: K, value: V, next: Entry<K, V>?) {
        val key: K = key
        var value = value
        var next: Entry<K, V>? = next
    }

    companion object {
        private const val INITIAL_CAPACITY = 16
    }

    constructor(): this(INITIAL_CAPACITY)

    constructor(capacity: Int) {
        this.capacity = capacity

        buckets = Array<Entry<K, V>?>(10) {
            null
        }
    }

    var capacity = 0
    var buckets: Array<Entry<K, V>?>
    var size = 0

    fun put(key: K, value: V) {
        val bucket = key.hashCode() % capacity
        var entry = buckets[bucket]

        while (entry != null) {
            if (entry.key?.equals(key) == true) {
                entry.value = value
                return
            } else {
                entry = entry.next
            }
        }

        val newEntry = Entry(key, value, null)
        newEntry.next = buckets[bucket]
        buckets[bucket] = newEntry
    }

    fun get(key: K): V? {
        val bucket = key.hashCode() % capacity
        var entry = buckets[bucket]

        while (entry != null) {
            if (entry.key?.equals(key) == true) {
                return entry.value
            } else {
                entry = entry.next
            }
        }

        return null
    }

    fun contains(key: K): Boolean {
        val bucket = key.hashCode() % capacity
        return buckets[bucket] != null
    }
}