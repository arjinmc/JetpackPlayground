package com.arjinmc.jetpackplayground.ui.constrainlayout

import android.os.Bundle
import android.view.View
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActConstrainLayoutLayerBinding

/**
 * base on ConstrainLayout 2.0+
 * Created by Eminem Lo on 11/9/21
 * email: arjinmc@hotmail.com
 */
class ConstrainLayoutLayerActivity : BasicActivity() {

    private val binding by viewBinding(ActConstrainLayoutLayerBinding::inflate)

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
        setTitle(R.string.constrain_layout_layer)

        //layer and flow cannot use at the same time
        binding.layer.visibility = View.GONE
    }

}
