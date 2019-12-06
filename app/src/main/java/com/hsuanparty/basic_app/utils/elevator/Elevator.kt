package com.hsuanparty.basic_app.utils.elevator

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/12/6
 * Description:
 */
class Elevator(maxLoading: Int, validFloor: List<Int>): LoadingDetector.ILoadingDetect,
    Button.IButtonListener {
    val maxLoading = maxLoading
    val validFloor = validFloor
    var currentLoading = 0
    private val beep = Beep()
    private val detector = LoadingDetector(maxLoading, this)
    private val door = Door()

    private val buttons = ArrayList<Button>()
    private val openButton = ElevatorButton(1001, this)
    private val closeButton = ElevatorButton(1002, this)
    private val emerButton = ElevatorButton(1003, this)

    init {
        detector.start()

        for (floor in validFloor) {
            buttons.add(ElevatorButton(floor, this))
        }
    }

    override fun onOverloading() {
        beep.warning()

        // force open door
    }
}