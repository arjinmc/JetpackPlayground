package com.arjinmc.jetpackplayground.ui.constrainlayout

import android.os.Bundle
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActConstrainLayoutBasicBinding
import com.arjinmc.jetpackplayground.databinding.ActConstrainLayoutConstrainSetBinding
import com.arjinmc.jetpackplayground.databinding.ActConstrainLayoutConstrainWidthBinding

/**
 * Created by Eminem Lo on 11/9/21
 * email: arjinmc@hotmail.com
 */
class ConstrainLayoutConstrainSetActivity : BasicActivity() {

    private val binding by viewBinding(ActConstrainLayoutConstrainSetBinding::inflate)

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
