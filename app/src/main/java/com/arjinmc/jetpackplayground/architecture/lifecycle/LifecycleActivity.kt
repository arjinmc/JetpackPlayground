package com.arjinmc.jetpackplayground.architecture.lifecycle

import android.os.Bundle
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
    }

    override fun initData() {
        setTitle(R.string.arch_lifecycle)
    }

}