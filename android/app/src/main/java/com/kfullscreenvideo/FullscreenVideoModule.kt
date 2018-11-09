package com.kfullscreenvideo

import android.content.Intent
import android.util.Log
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class FullscreenVideoModule(reactContext: ReactApplicationContext)
    : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String = "KFullscreenVideoPlayer"

    @ReactMethod
    fun playFullscreenVideo(videoUrl: String) {
        val intent = Intent(reactApplicationContext, FullscreenVideoActivity::class.java)
        intent.putExtra(FullscreenVideoActivity.EXTRA_VIDEO_URL, videoUrl)
        reactApplicationContext.startActivity(intent)
    }
    @ReactMethod
    fun playFullscreenVideoA() {
        val intent = Intent(reactApplicationContext, FullscreenVideoActivity::class.java)
        reactApplicationContext.startActivity(intent)
        Log.d("FullscreenVideoModule", "Got here..")
        Log.d("FullscreenVideoModule", "s:" + reactApplicationContext.currentActivity)
    }
}
