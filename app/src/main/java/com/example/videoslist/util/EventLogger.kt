package com.example.videoslist.util

import android.util.Log

object EventLogger {
    fun logEvent(event: String) {
        Log.d("EventLogger", event)
    }
}