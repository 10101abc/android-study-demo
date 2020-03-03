package com.example.myapplication

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_buypoint.*

class Activity_buypoint : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    private var basePoint = 8
    private var buyPoint = 27
    private var flag = 0
    private val pointmap: IntArray = intArrayOf(
        0, 1, 2, 3, 4, 5, 6, 8, 10, 13, 16
    )
    private var i = 0
    private lateinit var lastSeekBar: SeekBar
    private var pointBefore: IntArray = intArrayOf(0, 0, 0, 0, 0, 0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buypoint)
        setSystemBar(this)
        buy_cha.setOnSeekBarChangeListener(this)
        buy_con.setOnSeekBarChangeListener(this)
        buy_dex.setOnSeekBarChangeListener(this)
        buy_int.setOnSeekBarChangeListener(this)
        buy_str.setOnSeekBarChangeListener(this)
        buy_wis.setOnSeekBarChangeListener(this)
        buy_confirm.setOnClickListener {
            if (buyPoint > 0) timerToast( Toast.makeText(
                this,
                R.string.point_last_tip,
                Toast.LENGTH_LONG
            ),500L)
            else if (buyPoint < 0) timerToast( Toast.makeText(
                this,
                R.string.point_overflow_tip,
                Toast.LENGTH_SHORT
            ),500L)
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        flag++
        when (seekBar) {
            buy_str -> {
                i = 0
                execute(buy_str, str_value, i)
            }
            buy_dex -> {
                i = 1
                execute(buy_dex, dex_value, i)
            }
            buy_con -> {
                i = 2
                execute(buy_con, con_value, i)
            }
            buy_int -> {
                i = 3
                execute(buy_int, int_value, i)
            }
            buy_wis -> {
                i = 4
                execute(buy_wis, wis_value, i)
            }
            buy_cha -> {
                i = 5
                execute(buy_cha, cha_value, i)
            }
        }
        lastSeekBar = seekBar!!
        buy_value.text = buyPoint.toString()
        if (flag != 2) flag = 2
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        vibrate(this)
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        vibrate(this)
    }

    private fun buy(before: Int, progress: Int): Int {
        if (flag == 1) {
            when (progress) {
                1 -> buyPoint -= 1
                2 -> buyPoint -= 2
                3 -> buyPoint -= 3
                4 -> buyPoint -= 4
                5 -> buyPoint -= 5
                6 -> buyPoint -= 6
                7 -> buyPoint -= 8
                8 -> buyPoint -= 10
                9 -> buyPoint -= 13
                10 -> buyPoint -= 16
            }
        } else {
            buyPoint -= (pointmap[progress] - pointmap[before])
        }

        return progress
    }

    private fun execute(seekBar: SeekBar?, textView: TextView, i: Int) {
        if (seekBar != null) {
            textView.text = (seekBar.progress + basePoint).toString()
            pointBefore[i] = buy(pointBefore[i], seekBar.progress)
        }
    }
}