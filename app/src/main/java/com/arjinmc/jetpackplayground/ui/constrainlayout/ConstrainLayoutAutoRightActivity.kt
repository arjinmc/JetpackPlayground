package com.arjinmc.jetpackplayground.ui.constrainlayout

import android.os.Bundle
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActConstrainLayoutAutoRightBinding

/**
 * Auto on right
 * Created by Eminem Lo on 11/8/21
 * email: arjinmc@hotmail.com
 */
class ConstrainLayoutAutoRightActivity : BasicActivity() {

    private val binding by viewBinding(ActConstrainLayoutAutoRightBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    override fun initView() {

    }

    override fun initListener() {

        binding.btnChange.setOnClickListener {
            binding.tvContent.text = binding.etContent.text.toString()
        }
    }

    override fun initData() {
    }
}