package com.arjinmc.jetpackplayground.ui.constrainlayout

import android.os.Bundle
import android.widget.RadioGroup
import androidx.constraintlayout.helper.widget.Flow
import com.arjinmc.jetpackplayground.R
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

        binding.rgFlow.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                var mode = 0
                when (checkedId) {
                    R.id.rb_none -> mode = Flow.WRAP_NONE
                    R.id.rb_aligned -> mode = Flow.WRAP_ALIGNED
                    R.id.rb_chain -> mode = Flow.WRAP_CHAIN
                }
                binding.flow.setWrapMode(mode)
            }
        })

    }

    override fun initData() {
        setTitle(R.string.constrain_layout_flow)
    }

}
