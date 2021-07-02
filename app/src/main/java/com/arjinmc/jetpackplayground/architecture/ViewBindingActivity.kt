package com.arjinmc.jetpackplayground.architecture

import android.os.Bundle
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActViewBindingBinding

/**
 * Created by Eminem Lo on 7/2/21.
 * email: arjinmc@hotmail.com
 */
class ViewBindingActivity : BasicActivity() {

    private val binding by lazy { ActViewBindingBinding.inflate(layoutInflater) }

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
        binding.tvName.text = "name:arjinmc"
        binding.tvOrganization.text = "organization: CSG"
        binding.tvAddress.text = "address: Shenzhen,China"
    }
}