package com.arjinmc.jetpackplayground.ui.constrainlayout

import android.os.Bundle
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActConstrainLayoutBaselineBinding

/**
 * Created by Eminem Lo on 11/9/21
 * email: arjinmc@hotmail.com
 */
class ConstrainLayoutBaselineActivity : BasicActivity() {

    private val binding by viewBinding(ActConstrainLayoutBaselineBinding::inflate)

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
