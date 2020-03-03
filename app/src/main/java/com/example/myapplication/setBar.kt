package com.example.myapplication

import android.app.Activity
import android.content.res.Configuration
import android.graphics.Color
import android.view.View
import android.view.WindowManager

fun setSystemBar(activity:Activity){
    activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    activity.window.statusBarColor = Color.TRANSPARENT
    activity.window.navigationBarColor = Color.TRANSPARENT
    if (activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_NO) {
        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}