package com.hsuanparty.basic_app.utils.elevator

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/12/6
 * Description:
 */
class ElevatorButton(floor: Int, listener: IButtonListener): Button(floor, listener) {
    val floor = floor
}