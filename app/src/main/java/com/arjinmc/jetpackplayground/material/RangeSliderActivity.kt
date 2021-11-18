package com.arjinmc.jetpackplayground.material

import android.os.Bundle
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActRangeSliderBinding

/**
 * sample from https://github.com/Akanshi32/material-range-slider
 */
class RangeSliderActivity : BasicActivity() {

    private val binding by viewBinding(ActRangeSliderBinding::inflate)

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
    }
}