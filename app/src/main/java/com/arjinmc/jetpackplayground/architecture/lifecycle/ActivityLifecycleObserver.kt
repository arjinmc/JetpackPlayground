package com.arjinmc.jetpackplayground.architecture.lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Created by Eminem Lo on 12/17/21
 * email: arjinmc@hotmail.com
 */
class ActivityLifecycleObserver : DefaultLifecycleObserver {

    private var tag = "LifecycleObserver"

    constructor(clz: Class<*>) {
        tag = clz.simpleName
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.e(tag, "onCreate")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.e(tag, "onPause")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.e(tag, "onResume")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.e(tag, "onStart")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.e(tag, "onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.e(tag, "onDestroy")
    }

}