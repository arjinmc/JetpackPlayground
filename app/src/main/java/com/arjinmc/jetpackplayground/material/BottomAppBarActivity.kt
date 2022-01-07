package com.arjinmc.jetpackplayground.material

import android.os.Bundle
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActBottomAppBarBinding

/**
 * Created by Eminem Lo on 1/7/22
 * email: arjinmc@hotmail.com
 */
class BottomAppBarActivity : BasicActivity() {

    private val mBind by lazy { ActBottomAppBarBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBind.root)
    }

    override fun initView() {
    }

    override fun initListener() {
    }

    override fun initData() {
    }
}