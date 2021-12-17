package com.arjinmc.jetpackplayground.architecture.databinding

import android.os.Bundle
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActDataBindingBinding

/**
 * Created by Eminem Lo on 12/15/21
 * email: arjinmc@hotmail.com
 */
class DataBindingActivity : BasicActivity() {

    private val binding by lazy { ActDataBindingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    override fun initView() {
    }

    override fun initListener() {
    }

    override fun initData() {
        setTitle(R.string.arch_data_binding)
    }
}