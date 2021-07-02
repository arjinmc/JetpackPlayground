package com.arjinmc.jetpackplayground.architecture

import android.os.Bundle
import android.os.PersistableBundle
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.basic.BasicActivity

/**
 *
 * Created by Eminem Lo on 21/5/2021.
 * email: arjinmc@hotmail.com
 */
class RoomActivity: BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.act_room)
        init()
    }
    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initListener() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }
}