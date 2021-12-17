package com.arjinmc.jetpackplayground.architecture.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Eminem Lo on 12/15/21
 * email: arjinmc@hotmail.com
 */
class LiveDataViewModel : ViewModel() {

    val data: MutableLiveData<LiveDataBean> by lazy {
        MutableLiveData<LiveDataBean>()
    }

    fun setContent(content:String?){
        data.value?.content = content
    }
}