package com.arjinmc.jetpackplayground.architecture.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActLifecycleBinding
import com.arjinmc.jetpackplayground.util.IntentUtil

/**
 * Created by Eminem Lo on 12/17/21
 * email: arjinmc@hotmail.com
 */
class LifecycleActivity : BasicActivity() {

    private val binding by lazy { ActLifecycleBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    override fun initView() {
    }

    override fun initListener() {

        binding.btnLifecycleOwner.setOnClickListener {
            IntentUtil.startActivity(this, CustomLifecycleOwnerActivity::class.java)
        }
        lifecycle.addObserver(ActivityLifecycleObserver(LifecycleActivity::class.java))
        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                when (event) {
                    Lifecycle.Event.ON_CREATE -> Log.e("LifecycleEventObserver", "onCreate")
                    Lifecycle.Event.ON_RESUME -> Log.e("LifecycleEventObserver", "onResume")
                }
            }
        })
    }

    override fun initData() {
        setTitle(R.string.arch_lifecycle)
    }

}