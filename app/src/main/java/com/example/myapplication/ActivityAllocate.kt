package com.example.myapplication

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_allocate.*

class ActivityAllocate : AppCompatActivity(), View.OnDragListener, View.OnLongClickListener {

    private var result = intArrayOf(0, 0, 0, 0, 0, 0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_allocate)
        setSystemBar(this)
        val dice = intent.extras?.getIntArray("dice")
        one.text = dice?.get(0).toString()
        two.text = dice?.get(1).toString()
        three.text = dice?.get(2).toString()
        four.text = dice?.get(3).toString()
        five.text = dice?.get(4).toString()
        six.text = dice?.get(5).toString()

        allc_confirm.setOnClickListener {
            var a = -1
            for (i in result)
                if (i == 0) a = 1
            if (a == 1)
                timerToast(
                    Toast.makeText(
                        this,
                        R.string.allocate_have_not_finish,
                        Toast.LENGTH_LONG
                    ), 500L
                )
        }

        one.setOnLongClickListener(this)
        two.setOnLongClickListener(this)
        three.setOnLongClickListener(this)
        four.setOnLongClickListener(this)
        five.setOnLongClickListener(this)
        six.setOnLongClickListener(this)

        str_allc.setOnDragListener(this)
        dex_allc.setOnDragListener(this)
        con_allc.setOnDragListener(this)
        inte_allc.setOnDragListener(this)
        wis_allc.setOnDragListener(this)
        cha_allc.setOnDragListener(this)
    }

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        if (event?.action == DragEvent.ACTION_DROP)
            when (v) {
                str_allc -> {
                    result[0] = event.clipData.getItemAt(0).text.toString().toInt()
                    str_allc.text = "${result[0]}\n力量"
                    str_allc.setPadding(70, 95, 70, 0)
                }
                dex_allc -> {
                    result[1] = event.clipData.getItemAt(0).text.toString().toInt()
                    dex_allc.text = "${result[1]}\n敏捷"
                    v?.setPadding(70, 95, 70, 0)
                }
                con_allc -> {
                    result[2] = event.clipData.getItemAt(0).text.toString().toInt()
                    con_allc.text = "${result[2]}\n体质"
                    v?.setPadding(70, 95, 70, 0)
                }
                inte_allc -> {
                    result[3] = event.clipData.getItemAt(0).text.toString().toInt()
                    inte_allc.text = "${result[3]}\n智力"
                    v?.setPadding(70, 95, 70, 0)
                }
                wis_allc -> {
                    result[4] = event.clipData.getItemAt(0).text.toString().toInt()
                    wis_allc.text = "${result[4]}\n感知"
                    v?.setPadding(70, 95, 70, 0)
                }
                cha_allc -> {
                    result[5] = event.clipData.getItemAt(0).text.toString().toInt()
                    cha_allc.text = "${result[5]}\n魅力"
                    v?.setPadding(70, 95, 70, 0)
                }
            }
        return true
    }

    override fun onLongClick(v: View?): Boolean {
        val clipData: ClipData = when (v) {
            one -> ClipData.newPlainText("value", one.text)
            two -> ClipData.newPlainText("value", two.text)
            three -> ClipData.newPlainText("value", three.text)
            four -> ClipData.newPlainText("value", four.text)
            five -> ClipData.newPlainText("value", five.text)
            six -> ClipData.newPlainText("value", six.text)
            else -> ClipData.newPlainText("value", null)
        }
        v?.isInvisible=true
        v?.startDragAndDrop(clipData, View.DragShadowBuilder(v), null, 0)
        v?.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
        return true
    }
}
