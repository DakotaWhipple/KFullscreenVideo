package com.kfullscreenvideo

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.ViewManager
import android.view.WindowManager
import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.bridge.Arguments
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView


class FullscreenVideoActivity: ReactActivity() {
    val TAG = "FullscreenVideoActivity"
//    lateinit var videoView: VideoView
    companion object {
        const val EXTRA_VIDEO_URL = "VIDEO_URL"
        const val EXTRA_VIDEO_START_TIME = "VIDEO_CURRENT_TIME"
    }

    inner class FullscreenVideoActivityDelegate(val activity: Activity, val mainComponentName: String)
            : ReactActivityDelegate(activity, mainComponentName) {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val ui = FullscreenVideoUI()
            ui.setContentView(this@FullscreenVideoActivity)

            val videoProperties = getVideoProperties(intent)
            Log.d(TAG, ""+videoProperties)

            // Setup window
            fullscreenWindow()
            window.setFormat(PixelFormat.TRANSLUCENT)

//            val trackSelector = DefaultTrackSelector()
            val exoPlayer = ExoPlayerFactory.newSimpleInstance(this@FullscreenVideoActivity)
            ui.exoPlayerView.player = exoPlayer

            val dataSourceFactory = DefaultDataSourceFactory(this@FullscreenVideoActivity,
                    Util.getUserAgent(this@FullscreenVideoActivity, "KFullscreenVideo"))
            val videoSource = ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(videoProperties.uri)
            exoPlayer.prepare(videoSource)
            // Init mediaController
//            val mediaController = MediaController(this@FullscreenVideoActivity)
//            mediaController.setAnchorView(videoView)

            // Setup VideoView
            /*videoView.setMediaController(mediaController)
            videoView.setVideoURI(videoProperties.uri)
            videoView.requestFocus()
            videoView.setOnCompletionListener {
    //            this@FullscreenVideoActivity.finish()
            }

            videoView.setOnPreparedListener {
    //            exo_progress.isEnabled = false
//                loadingDialog.dismiss()
//                videoView.seekTo(videoProperties.playbackTime)
                videoView.resume()
            }*/
        }
    }

    override fun onPause() {
        super.onPause()
        if (isFinishing) {
            val params = Arguments.createMap()
//            params.putInt(EXTRA_VIDEO_START_TIME, videoView.currentPosition)

            /*reactInstanceManager
                    .currentReactContext!!
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
                    .emit("toggleFullscreen", params)*/
        }
    }

    fun fullscreenWindow() {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
    }

    fun getVideoProperties(i: Intent): VideoProperties =
            VideoProperties(Uri.parse(i.getStringExtra(EXTRA_VIDEO_URL)),
                    i.getIntExtra(EXTRA_VIDEO_START_TIME, 0))

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        // No man's land
    }

    override fun getMainComponentName(): String? {
//        return "KFullscreenVideoPlayer"
        return "KVideoPlayer"
    }

    override fun createReactActivityDelegate(): ReactActivityDelegate {
        return FullscreenVideoActivityDelegate(this, mainComponentName!!)
    }
}

class FullscreenVideoUI: AnkoComponent<FullscreenVideoActivity> {
    lateinit var exoPlayerView: PlayerView

    override fun createView(ui: AnkoContext<FullscreenVideoActivity>): View = with(ui) {
        frameLayout {
            exoPlayerView = playerView {

            }.lparams(width = matchParent, height = matchParent)
        }
    }

}

inline fun ViewManager.playerView(theme: Int = 0) = playerView(theme) {}
inline fun ViewManager.playerView(theme: Int = 0, init: PlayerView.() -> Unit): PlayerView {
    return ankoView({ PlayerView(it) }, theme, init)
}
