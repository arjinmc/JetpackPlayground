package com.arjinmc.jetpackplayground

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.util.IntentUtil

/**
 * Created by Eminem Lo on 7/7/21.
 * email: arjinmc@hotmail.com
 */
class SlashActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.act_splash)

        IntentUtil.startActivity(this, MainActivity::class.java)
        finish()
    }

    override fun initView() {
    }

    override fun initListener() {
    }

    override fun initData() {
    }
}