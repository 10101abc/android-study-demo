package com.example.myapplication

import android.widget.Toast
import java.util.*


fun timerToast(toast: Toast, cnt: Long) {
    val timer = Timer()
    timer.schedule(object : TimerTask() {
        override fun run() {
            toast.show()
        }
    }, 0, 3000)
    Timer().schedule(object : TimerTask() {
        override fun run() {
            toast.cancel()
            timer.cancel()
        }
    }, cnt)
}