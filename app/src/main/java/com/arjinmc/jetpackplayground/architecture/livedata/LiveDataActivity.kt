package com.arjinmc.jetpackplayground.architecture.livedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActLiveDataBinding

/**
 * Created by Eminem Lo on 12/15/21
 * email: arjinmc@hotmail.com
 */
class LiveDataActivity : BasicActivity() {

    private val binding by lazy { ActLiveDataBinding.inflate(layoutInflater) }
    private val mModel: LiveDataViewModel by viewModels()
    private val mData = MutableLiveData<LiveDataBean>()

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
        setTitle(R.string.arch_live_data)

        val observer = Observer<LiveDataBean> { data ->
            binding.tvContentViewModel.text = "view model:" + data.content
        }
        mModel.data.observe(this, observer)


        var dataObserver = Observer<LiveDataBean> { data ->
            binding.tvContentLiveData.text = "live data:" + data.content
        }
        mData.observe(this, dataObserver)

        binding.btnChange.setOnClickListener {
            mModel.data.value?.content = binding.etContent.text.toString()
            //do something to change
            mModel.data.value = mModel.data.value
            //or call onChanged
//            observer.onChanged(mModel.data.value)

            mData.value?.content = binding.etContent.text.toString()
            //do some thing to change
            mData.value = mData.value
            //or call onChanged
//            dataObserver.onChanged(mData.value)
        }

        mModel.data.value = LiveDataBean()
        mModel.data.value?.content = "view"
        mData.value = LiveDataBean()
    }

}