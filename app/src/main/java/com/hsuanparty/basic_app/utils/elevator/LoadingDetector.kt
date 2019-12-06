package com.hsuanparty.basic_app.utils.elevator

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/12/6
 * Description:
 */
class LoadingDetector(maxLoading: Int, listener: ILoadingDetect) {
    val maxLoading = maxLoading
    val listener = listener
    var currentLoading = 0


    init {
        // Keep detect loading while elevator is running
    }

    fun start() {
    }

    fun onReceiveLoading(loading: Int) {
        currentLoading = loading

        if (currentLoading >= maxLoading) {
            // overloading
            listener.onOverloading()
        }
    }

    fun isOverLoading(): Boolean {
        return currentLoading >= maxLoading
    }

    interface ILoadingDetect {
        fun onOverloading()
    }
}