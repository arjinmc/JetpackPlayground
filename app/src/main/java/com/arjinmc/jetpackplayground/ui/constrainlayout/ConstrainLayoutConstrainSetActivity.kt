package com.arjinmc.jetpackplayground.ui.constrainlayout

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActConstrainLayoutConstrainSetBinding

/**
 * Change constrain with codes
 * base on API 19
 * Created by Eminem Lo on 11/9/21
 * email: arjinmc@hotmail.com
 */
class ConstrainLayoutConstrainSetActivity : BasicActivity() {

    private val mOriginSet = ConstraintSet()
    private val mChangeSet = ConstraintSet()

    private var mIsChanged = false

    private val binding by viewBinding(ActConstrainLayoutConstrainSetBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    override fun initView() {

        mOriginSet.clone(binding.layoutCl)

        mChangeSet.clone(binding.layoutCl)
        mChangeSet.connect(R.id.tv_2, ConstraintSet.LEFT, R.id.tv_3, ConstraintSet.RIGHT)
        mChangeSet.connect(R.id.tv_3, ConstraintSet.LEFT, R.id.tv_1, ConstraintSet.RIGHT)
        mChangeSet.centerHorizontally(R.id.tv_3, ConstraintSet.PARENT_ID)

        TransitionManager.beginDelayedTransition(binding.layoutCl)

    }

    override fun initListener() {

        binding.btnChange.setOnClickListener {
            if (mIsChanged) {
                mOriginSet.applyTo(binding.layoutCl)
            } else {
                mChangeSet.applyTo(binding.layoutCl)
            }
            mIsChanged = !mIsChanged
        }
    }

    override fun initData() {
        setTitle(R.string.constrain_layout_constrain_set)
    }

}
