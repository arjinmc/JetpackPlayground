package com.arjinmc.jetpackplayground.architecture.lifecycle

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

/**
 * Created by Eminem Lo on 12/17/21
 * email: arjinmc@hotmail.com
 * reference to https://developer.android.com/topic/libraries/architecture/lifecycle
 */
class CustomLifecycleOwnerActivity : Activity(), LifecycleOwner {

    private lateinit var lifecycleRegistry: LifecycleRegistry

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.currentState = Lifecycle.State.CREATED

        lifecycle.addObserver(ActivityLifecycleObserver(CustomLifecycleOwnerActivity::class.java))
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    override fun onRestart() {
        super.onRestart()
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    override fun onResume() {
        super.onResume()
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED
    }

    override fun onPause() {
        super.onPause()
        //no onPause state
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    override fun onStop() {
        super.onStop()
        //no onStop state
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    override fun onDestroy() {
        super.onDestroy()
        //no onStop state
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

}