package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dicepoint.*


class Activity_dicepoint : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dicepoint)
        setSystemBar(this)

        var dice : IntArray?=null
        getdice.setOnClickListener {
          var result: IntArray=intArrayOf(
                dice_result(),
                dice_result(),
                dice_result(),
                dice_result(),
                dice_result(),
                dice_result()
            )
            diceresult.text =
                "${result[0]}, ${result[1]}, ${result[2]}, ${result[3]}, ${result[4]}, ${result[5]}"
            vibrate(this)
            dice=result
        }
        getdice.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) vibrate(this)
            false
        }
        dice_confirm.setOnClickListener {
            if (dice == null) timerToast( Toast.makeText(this, R.string.have_not_dice_tip, Toast.LENGTH_SHORT),500L)
        }
    }

    private fun dice_result(): Int {
        val dice: MutableList<Int> =
            mutableListOf((1..6).random(), (1..6).random(), (1..6).random(), (1..6).random())
        dice.sortBy { it }
        dice.removeAt(0)
        var a = 0
        for (i in dice) {
            a += i
        }
        return a
    }
}