package com.kfullscreenvideo

import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import android.widget.MediaController
import android.widget.VideoView
import com.facebook.react.ReactActivity
import com.facebook.react.bridge.Arguments
import com.facebook.react.modules.core.DeviceEventManagerModule
import kotlinx.android.synthetic.main.video_frame_layout.*


class FullscreenVideoActivity: ReactActivity() {
    companion object {
        const val EXTRA_VIDEO_URL = "VIDEO_URL"
        const val EXTRA_VIDEO_START_TIME = "VIDEO_CURRENT_TIME"
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.video_frame_layout)
        val videoProperties = getVideoProperties(intent)

        // Setup window
        fullscreenWindow()
        window.setFormat(PixelFormat.TRANSLUCENT)

        // Loading dialog
        val loadingDialog = showLoadingDialog()

        // Init mediaController
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        // Setup VideoView
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(videoProperties.uri)
        videoView.requestFocus()
        videoView.setOnCompletionListener {
//            this@FullscreenVideoActivity.finish()
        }

        videoView.setOnPreparedListener {
//            exo_progress.isEnabled = false
            loadingDialog.dismiss()
            videoView.seekTo(videoProperties.playbackTime)
            videoView.resume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (isFinishing) {
            val params = Arguments.createMap()
            params.putInt(EXTRA_VIDEO_START_TIME, videoView.currentPosition)

            reactInstanceManager
                    .currentReactContext!!
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
                    .emit("toggleFullscreen", params)
        }
    }

    fun fullscreenWindow() {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
    }

    fun getVideoProperties(i: Intent): VideoProperties =
            VideoProperties(Uri.parse(i.getStringExtra(EXTRA_VIDEO_URL)),
                    i.getIntExtra(EXTRA_VIDEO_START_TIME, 0))

    fun showLoadingDialog() =
            ProgressDialog.show(this,
                    "",
                    "Buffering video...",
                    true,
                    true)
}

