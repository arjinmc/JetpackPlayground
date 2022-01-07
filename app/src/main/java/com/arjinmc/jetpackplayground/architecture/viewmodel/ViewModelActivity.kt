package com.arjinmc.jetpackplayground.architecture.viewmodel

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActViewModelBinding

/**
 * Created by Eminem Lo on 12/8/21
 * email: arjinmc@hotmail.com
 */
class ViewModelActivity : BasicActivity() {

    private val binding by lazy { ActViewModelBinding.inflate(layoutInflater) }

    private val mViewModel: MyViewModel by viewModels()

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
        setTitle(R.string.arch_view_model)

//         var viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        mViewModel.setData(UserBean(1, "Jin"))
        binding.tvContent.text =
            "id:" + mViewModel.getData()?.id + ",name:" + mViewModel.getData()?.name
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        mViewModel.loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelStore.clear()
    }
}