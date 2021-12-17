package com.arjinmc.jetpackplayground.ui.constrainlayout

import android.os.Bundle
import android.view.View
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActConstrainLayoutGroupBinding

/**
 * Created by Eminem Lo on 11/9/21
 * email: arjinmc@hotmail.com
 */
class ConstrainLayoutGroupActivity : BasicActivity() {

    private val binding by viewBinding(ActConstrainLayoutGroupBinding::inflate)
    private var mIsShown = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    override fun initView() {

    }

    override fun initListener() {

        binding.btnChange.setOnClickListener {
            if (mIsShown) {
                binding.group.visibility = View.GONE
            } else {
                binding.group.visibility = View.VISIBLE
            }
            mIsShown = !mIsShown
            binding.btnChange.text = if (!mIsShown) "shown" else "hide"
        }
    }

    override fun initData() {
        setTitle(R.string.constrain_layout_group)
    }

}
