package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSystemBar(this)
        pointChoice.setOnCheckedChangeListener {_, checkedId ->
            vibrate(this)
            point_tip.text = when(checkedId){
                R.id.dicepoint -> getString(R.string.dice_point_info)
                R.id.buypoint -> getString(R.string.buy_point_info)
                else -> getString(R.string.choice_tip)
            }
        }
        button.setOnClickListener {
            when(pointChoice.checkedRadioButtonId){
                R.id.dicepoint -> startActivity(Intent(this,Activity_dicepoint::class.java))
                R.id.buypoint -> startActivity(Intent(this,Activity_buypoint::class.java))
                else -> timerToast( Toast.makeText(this, "请选择一个方法", Toast.LENGTH_SHORT),500L)
            }
            vibrate(this)
        }
        button.setOnTouchListener { _, event ->
            if(event.action==MotionEvent.ACTION_DOWN) vibrate(this)
            false
        }
    }
}
