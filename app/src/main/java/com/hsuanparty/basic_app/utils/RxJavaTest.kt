package com.hsuanparty.basic_app.utils

import android.util.Log
import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.observables.GroupedObservable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Timed
import java.util.concurrent.TimeUnit
import javax.security.auth.Subject

class RxJavaTest {

    companion object {
        private const val TAG = "rxjava"
    }

    fun test() {
        // Part 9, RxJava: Different types of Subjects

        // Part 8, RxJava: Different types of Observables

        // Part 7, Mathematical and Aggregate Operators

        // Part 6, Conditional and Boolean Operators
        takeWhile()
//        takeUntil()
//        skipWhile()
//        skipUntil()
//        contains()
//        all()

        // Part 5, Utility Operators
//        timeout()
//        timeInterval()
//        subscribeOn()
//        observeOn()
//        materialize()
//        operatorDo()
//        delay()

        // Part 4, Operators for Combining Observables
//        switchOnNext()
//        zip()
//        concat()
//        merge()
//        join()
//        combineLatest()

        // Part 3, Operators for Filtering Observables
//        takeLast()
//        take()
//        skipLast()
//        skip()
//        sample()
//        ignoreElements()
//        filter()
//        elementAt()
//        distinct()

        // Part 2, Operators for Transforming Observables
//        scan()
//        groupBy()
//        concatMap()
//        switchMap()
//        flatMap()
//        map()
//        buffer()
    }

    private fun takeWhile() {
        val ob1 = Observable.create<Int> { emitter ->
            for (i in 0..6) {
                Thread.sleep(1000)
                emitter.onNext(i)
            }
            emitter.onComplete()
        }
            .takeWhile { num ->
                num < 2
            }

        val ob2 = Observable.timer(3, TimeUnit.SECONDS)
            .flatMap { _ ->
                Observable.just(11, 12, 13, 14, 15, 16)
            }

        ob1.subscribe(object: Observer<Int> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Int) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun takeUntil() {
        val ob1 = Observable.create<Int> { emitter ->
            for (i in 0..6) {
                Thread.sleep(1000)
                emitter.onNext(i)
            }
            emitter.onComplete()
        }

        val ob2 = Observable.timer(3, TimeUnit.SECONDS)
            .flatMap { _ ->
                Observable.just(11, 12, 13, 14, 15, 16)
            }

        ob1.takeUntil(ob2)
            .subscribe(object: Observer<Int> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Int) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun skipWhile() {
        Observable.create<Int> { emitter ->
            for (i in 0..6) {
                Thread.sleep(1000)
                emitter.onNext(i)
            }
            emitter.onComplete()
        }
            .skipWhile { num ->
                num < 2
            }
            .subscribe(object: Observer<Int> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Int) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    private fun skipUntil() {
        val ob1 = Observable.create<Int> { emitter ->
            for (i in 0..6) {
                Thread.sleep(1000)
                emitter.onNext(i)
            }
            emitter.onComplete()
        }

        val ob2 = Observable.timer(3, TimeUnit.SECONDS)
            .flatMap { _ ->
                Observable.just(11, 12, 13, 14, 15, 16)
            }

        ob1.skipUntil(ob2)
            .subscribe(object: Observer<Int> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Int) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun contains() {
        Observable.just(1, 2, 3, 4, 5, 6)
            .contains(10)
            .subscribe(object: SingleObserver<Boolean> {
                override fun onSuccess(t: Boolean) {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    private fun all() {
        Observable.just(0, 1, 2, 3, 4, 0, 6, 0)
            .all { item ->
                item > 0
            }
            .subscribe(object: SingleObserver<Boolean> {
                override fun onSuccess(t: Boolean) {
                    Log.d(TAG, "onSuccess: $t")
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun timeout() {
        Observable.interval(1, TimeUnit.SECONDS)
            .timeout(500, TimeUnit.MILLISECONDS)
            .subscribe(object: Observer<Long> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Long) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError: $e")
                    e.printStackTrace()
                }
            })

    }

    private fun timeInterval() {
        Observable.interval(100, TimeUnit.MILLISECONDS)
            .take(3)
            .timeInterval()
            .subscribe {
                Log.d(TAG, "onNext: ${it.time()}, ${it.value()}")
            }
    }

    private fun subscribeOn() {
        Observable.range(1, 5)
            .map { i ->
                i * 100
            }
            .doOnNext { i ->
                Log.d(TAG, "Emitting $i on thread ${Thread.currentThread().name}")
            }
            .subscribeOn(Schedulers.computation())
            .map { i ->
                i * 10
            }
            .subscribe { i ->
                Log.d(TAG, "Received $i on thread ${Thread.currentThread().name}")
            }
    }

    private fun observeOn() {
        Observable.just("A", "BB", "CCC", "DDDD", "EEEEE", "FFFFFF")
            .map { str ->
                str.length
            }
            .observeOn(Schedulers.computation())
            .map { length ->
                length * 2
            }
            .subscribe(object: Observer<Int> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Int) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun materialize() {
        Observable.just("A", "B", "C", "D", "E", "F")
            .materialize()
            .subscribe(object: Observer<Notification<String>> {
                override fun onComplete() {
                    Log.d(TAG, "YYYYYYYYYYYYYY")
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Notification<String>) {
                    if (t.isOnNext) {
                        Log.d(TAG, "${t.value}")
                    }

                    if (t.isOnComplete) {
                        Log.d(TAG, "GGGGGGGGGGGGGGGG")
                    }
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun operatorDo() {
        Observable.just("A", "B", "C", "D", "E", "F")
            .doOnNext {
                Log.d(TAG, "doOnNext: $it")
            }
            .subscribe()

        Observable.range(1, 5)
            .doOnEach(object: Observer<Int> {
                override fun onComplete() {
                    Log.d(TAG, "onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe")
                }

                override fun onNext(t: Int) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                }
            })
            .doOnSubscribe {
                Log.d(TAG, "doOnSubscribe is called")
            }
            .subscribe(object: Observer<Int> {
                override fun onComplete() {
                    Log.d(TAG, "onComplete11")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe11")
                }

                override fun onNext(t: Int) {
                    Log.d(TAG, "onNext11: $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun delay() {
        Observable.just("A", "B", "C", "D", "E")
            .delay(5, TimeUnit.SECONDS)
            .subscribe(object: Observer<String> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: String) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun switchOnNext() {
        Observable.switchOnNext(Observable.interval(600, TimeUnit.MILLISECONDS)
            .map { aLong ->
                Observable.interval(180, TimeUnit.MILLISECONDS)
            })
            .subscribe { item ->
                Log.d(TAG, "onNext: $item")
            }
    }

    private fun zip() {
        val alphabets1 = Observable.interval(1, TimeUnit.SECONDS).map { id ->
            "A$id"
        }

        val alphabets2 = Observable.interval(5, TimeUnit.SECONDS).map { id ->
            "B$id"
        }

        Observable.zip(alphabets1, alphabets2, BiFunction<String, String, String> { s1, s2 ->
            "$s1 $s2"
        }).subscribe(object: Observer<String> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
                Log.d(TAG, "onNext: $t")
            }

            override fun onError(e: Throwable) {
            }

        })
    }

    private fun concat() {
        val alphabets1 = Observable.interval(1, TimeUnit.SECONDS).map { id ->
            "A$id"
        }

        val alphabets2 = Observable.interval(5, TimeUnit.SECONDS).map { id ->
            "B$id"
        }

        Observable.concat(alphabets1, alphabets2)
            .subscribe(object: Observer<String> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: String) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun merge() {
        val alphabets1 = Observable.interval(1, TimeUnit.SECONDS).map { id ->
            "A$id"
        }

        val alphabets2 = Observable.interval(5, TimeUnit.SECONDS).map { id ->
            "B$id"
        }

        Observable.merge(alphabets1, alphabets2)
            .subscribe(object: Observer<String> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: String) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun join() {
        val left = Observable.interval(100, TimeUnit.MILLISECONDS)
        val right = Observable.interval(100, TimeUnit.MILLISECONDS)

        left.join(right,
            Function { aLong: Long ->
                Observable.timer(0, TimeUnit.SECONDS)
            },
            Function { aLong: Long ->
                Observable.timer(0, TimeUnit.SECONDS)
            },
            BiFunction { t1: Long, t2: Long ->
                Log.d(TAG, "left result: $t1, right result: $t2")
                t1 + t2
            })
            .subscribe(object: Observer<Long> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Long) {
                    Log.d(TAG, "onNext(): $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun combineLatest() {
        val observable1 = Observable.interval(100, TimeUnit.MILLISECONDS)
        val observable2 = Observable.interval(150, TimeUnit.MILLISECONDS)

        Observable.combineLatest(observable1, observable2, BiFunction { t1: Long, t2: Long ->
            "Refresh observable1: $t1 refresh observable2: $t2"
        }).subscribe { item ->
              Log.d(TAG, "$item")
          }
    }

    private fun takeLast() {
        Observable.just("A", "B", "C", "D", "E", "F", "G", "H", "I")
            .takeLast(4)
            .subscribe(object: Observer<String> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: String) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun take() {
        Observable.just("A", "B", "C", "D", "E", "F", "G", "H", "I")
            .take(4)
            .subscribe(object: Observer<String> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: String) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun skipLast() {
        Observable.just("A", "B", "C", "D", "E", "F", "G", "H", "I")
            .skipLast(4)
            .subscribe(object: Observer<String> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: String) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun skip() {
        Observable.just("A", "B", "C", "D", "E", "F", "G", "H", "I")
            .skip(4)
            .subscribe(object: Observer<String> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: String) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun sample() {
        val timedObservable = Observable.just(1, 2, 3, 4, 5, 6, 7)
            .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), BiFunction { item: Int, time: Long -> item})

        timedObservable
            .sample(2, TimeUnit.SECONDS)
            .subscribe(object: Observer<Any> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Any) {
                    Log.d(TAG, "onNext(): $t")
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    private fun ignoreElements() {
        Observable.range(1, 10)
            .ignoreElements()
            .subscribe(object: CompletableObserver {
                override fun onComplete() {
                    Log.d(TAG, "onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe")
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError")
                }
            })
    }

    private fun filter() {
        Observable.just(1, 2, 3, 4, 5, 6)
            .filter { input ->
                input % 2 == 0
            }
            .subscribe(object: Observer<Int> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Int) {
                    Log.d(TAG, "onNext(): $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun elementAt() {
        Observable.just(1, 2, 3, 4, 5, 6)
            .elementAt(1)
            .subscribe(object: MaybeObserver<Int> {
                override fun onSuccess(t: Int) {
                    Log.d(TAG, "onSuccess(): $t")
                }

                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun distinct() {
        Observable.just(10, 20, 20, 10, 30, 40, 70, 60, 70)
            .distinct()
            .subscribe(object: Observer<Int> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Int) {
                    Log.d(TAG, "onNext(): $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun scan() {
        Observable.range(1, 10)
            .scan { input1, input2 ->
                input1 + input2
            }
            .subscribe(object: Observer<Int> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Int) {
                    Log.d(TAG, "onNext(): $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun groupBy() {
        Observable.range(1, 10)
            .groupBy { input ->
                input % 2 == 0
            }
            .subscribe(object: Observer<GroupedObservable<Boolean, Int>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: GroupedObservable<Boolean, Int>) {
                    if (t.key == true) {
                        t.subscribe(object: Observer<Int> {
                            override fun onComplete() {
                            }

                            override fun onSubscribe(d: Disposable) {
                            }

                            override fun onNext(t: Int) {
                                Log.d(TAG, "onNext: $t")
                            }

                            override fun onError(e: Throwable) {
                            }
                        })
                    }
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    private fun concatMap() {
        getOriginalObservable()
            .concatMap { input ->
                getModifiedObservable(input)
            }
            .subscribeOn(Schedulers.io())
            .subscribe(object: Observer<Int> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Int) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun switchMap() {
        getOriginalObservable()
            .switchMap { input ->
                getModifiedObservable(input).delay(1, TimeUnit.SECONDS)
            }
            .subscribeOn(Schedulers.io())
            .subscribe(object: Observer<Int> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Int) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun flatMap() {
        getOriginalObservable()
            .flatMap { input ->
                getModifiedObservable(input)
            }
            .subscribeOn(Schedulers.io())
            .subscribe(object: Observer<Int> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Int) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun map() {
        getOriginalObservable()
            .map { input ->
                input * 2
            }
            .subscribeOn(Schedulers.io())
            .subscribe(object: Observer<Int> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Int) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun getModifiedObservable(input: Int): Observable<Int> {
        return Observable.create { emitter ->
            emitter.onNext(input * 2)
            emitter.onComplete()
        }
    }

    private fun getOriginalObservable(): Observable<Int> {
        val integers = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8)

        return Observable.create { emitter ->
            for (num in integers) {
                if (!emitter.isDisposed) {
                    emitter.onNext(num)
                }
            }

            if(!emitter.isDisposed) {
                emitter.onComplete();
            }
        }
    }

    private fun buffer() {
        Observable.just("A", "B", "C", "D", "E", "F")
            .buffer(2)
            .subscribe(object: Observer<List<String>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: List<String>) {
                    Log.d(TAG, "onNext()")

                    for (str in t) {
                        Log.d(TAG, "String: $str")
                    }
                }

                override fun onError(e: Throwable) {
                }
            })
    }
}