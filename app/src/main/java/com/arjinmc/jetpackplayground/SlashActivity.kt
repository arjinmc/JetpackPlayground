package com.arjinmc.jetpackplayground

import android.os.Bundle
import com.arjinmc.jetpackplayground.basic.BasicActivity

/**
 * Created by Eminem Lo on 7/7/21.
 * email: arjinmc@hotmail.com
 */
class SlashActivity : BasicActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        installSplashScreen()
        setContentView(R.layout.act_splash)
    }

    override fun initView() {
    }

    override fun initListener() {
    }

    override fun initData() {
    }
}