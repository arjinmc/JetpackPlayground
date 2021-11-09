package com.arjinmc.jetpackplayground.ui.constrainlayout

import android.os.Bundle
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActConstrainLayoutFlowBinding

/**
 * base on ConstrainLayout 2.0+
 * Created by Eminem Lo on 11/9/21
 * email: arjinmc@hotmail.com
 */
class ConstrainLayoutFlowActivity : BasicActivity() {

    private val binding by viewBinding(ActConstrainLayoutFlowBinding::inflate)

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
