package com.kfullscreenvideo

import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager

class FullscreenVideoPackage: ReactPackage {
    override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule>
            = listOf<NativeModule>(FullscreenVideoModule(reactContext))

    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>>
            = listOf()

}