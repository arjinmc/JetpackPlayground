package com.arjinmc.jetpackplayground.architecture.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.arjinmc.jetpackplayground.basic.BasicFragment
import com.arjinmc.jetpackplayground.databinding.FragViewModelBinding

/**
 * Created by Eminem Lo on 12/8/21
 * email: arjinmc@hotmail.com
 */
class ViewModelFragment : BasicFragment() {

    private val binding by lazy { FragViewModelBinding.inflate(layoutInflater) }

    //    private val viewModel: MyViewModel by activityViewModels()
    private var viewModel: MyViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun initView() {
        viewModel = ViewModelProvider(this)[MyViewModel::class.java]
    }

    override fun initListener() {
    }

    override fun initData() {
        viewModel?.setData(UserBean(2, "Jetpack"))
        binding.tvContent.text =
            "id:" + viewModel?.getData()?.id + ",name:" + viewModel?.getData()?.name

    }

}