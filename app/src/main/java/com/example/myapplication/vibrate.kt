@file:Suppress("DEPRECATION")

package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.os.Vibrator

fun vibrate(activity: Activity){
    val v = activity.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (v.hasVibrator())v.vibrate(12)
}
