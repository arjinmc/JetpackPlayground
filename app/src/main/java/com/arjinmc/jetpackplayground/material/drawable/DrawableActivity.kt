package com.arjinmc.jetpackplayground.material.drawable

import android.os.Bundle
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActDrawableBinding

/**
 * Created by Eminem Lo on 6/29/22
 * email: arjinmc@hotmail.com
 */
class DrawableActivity : BasicActivity() {

    private lateinit var binding: ActDrawableBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActDrawableBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }

    override fun initView() {
        binding.tvTicket2.background = TicketDrawable()

    }

    override fun initListener() {

    }

    override fun initData() {

    }
}