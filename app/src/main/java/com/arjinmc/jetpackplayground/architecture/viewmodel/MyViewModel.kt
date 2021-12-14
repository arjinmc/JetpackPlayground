package com.arjinmc.jetpackplayground.architecture.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

/**
 *
 * Created by Eminem Lo on 12/13/21
 * email: arjinmc@hotmail.com
 */
class MyViewModel : ViewModel(), IViewModel<UserBean> {

    private var mData: UserBean? = null

    override fun setData(data: UserBean?) {
        mData = data
    }

    override fun getData(): UserBean? {
        return mData
    }

    override fun loadData() {
        Log.e("loadData", mData.toString())
    }
}