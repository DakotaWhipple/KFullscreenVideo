package com.kfullscreenvideo

import android.content.Intent
import android.util.Log
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class FullscreenVideoManager(reactContext: ReactApplicationContext)
    : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String = "KFullscreenVideo"

    @ReactMethod
    fun playFullscreenVideo(videoUrl: String) {
        val intent = Intent(reactApplicationContext, FullscreenVideoActivity::class.java)
        intent.putExtra(FullscreenVideoActivity.EXTRA_VIDEO_URL, videoUrl)
//        intent.putExtra(FullscreenVideoActivity.EXTRA_VIDEO_START_TIME, videoStartTime)
        reactApplicationContext.startActivity(intent)
        Log.d("FullscreenVid", "Got here")
    }
}
