package com.kfullscreenvideo

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log

import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate

class MainActivity : ReactActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    override fun getMainComponentName(): String? {
        return "KVideoPlayer"
    }

    override fun createReactActivityDelegate(): ReactActivityDelegate {
        return MainActivityDelegate(this, mainComponentName!!)
    }

    inner class MainActivityDelegate(val activity: Activity, val mainComponentName: String)
            : ReactActivityDelegate(activity, mainComponentName) {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            Log.d("MainActivity", "Got here....")
        }

    }
}
