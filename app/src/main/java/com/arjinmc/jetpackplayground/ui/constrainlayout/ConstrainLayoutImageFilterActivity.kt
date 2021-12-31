package com.arjinmc.jetpackplayground.ui.constrainlayout

import android.os.Bundle
import android.widget.RadioGroup
import androidx.constraintlayout.helper.widget.Flow
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActConstrainLayoutFlowBinding
import com.arjinmc.jetpackplayground.databinding.ActConstrainLayoutImageFilterBinding

/**
 * base on ConstrainLayout 2.0+
 * Created by Eminem Lo on 12/31/21
 * email: arjinmc@hotmail.com
 */
class ConstrainLayoutImageFilterActivity : BasicActivity() {

    private val binding by viewBinding(ActConstrainLayoutImageFilterBinding::inflate)

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
        setTitle(R.string.constrain_layout_flow)
    }

}
