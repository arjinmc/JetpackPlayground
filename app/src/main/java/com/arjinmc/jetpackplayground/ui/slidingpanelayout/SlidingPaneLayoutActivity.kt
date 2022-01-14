package com.arjinmc.jetpackplayground.ui.slidingpanelayout

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActSlidingPaneLayoutBinding

/**
 * When Screen size smaller than 600dp, it can slide;
 * When Screen size smaller than 600dp, it will be divided to two pane
 * Created by Eminem Lo on 1/13/22
 * email: arjinmc@hotmail.com
 */
class SlidingPaneLayoutActivity : BasicActivity() {

    private val binding by lazy { ActSlidingPaneLayoutBinding.inflate(layoutInflater) }

    private var mIsOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    override fun initView() {
        setTitle(R.string.ui_sliding_pane_layout)

        Log.e("init", "isEnable:${binding.splLayout.isSlideable}")
    }

    override fun initListener() {

        binding.btnChange.setOnClickListener {
            if (mIsOpen) {
                binding.splLayout.close()
            } else {
                binding.splLayout.openPane()
            }
            mIsOpen = !mIsOpen
        }
        binding.splLayout.addPanelSlideListener(object : SlidingPaneLayout.PanelSlideListener {
            override fun onPanelSlide(panel: View, slideOffset: Float) {
            }

            override fun onPanelOpened(panel: View) {
            }

            override fun onPanelClosed(panel: View) {
            }
        })
    }

    override fun initData() {
    }
}