package com.arjinmc.jetpackplayground.basic

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Basic activity
 * Created by Eminem Lo on 21/5/2021.
 * email: arjinmc@hotmail.com
 */
abstract class BasicActivity : AppCompatActivity(), BasicActivityInterface {

    open fun getContext(): Context {
        return this
    }

    open fun init() {
        initView()
        initListener()
        initData()
    }

    inline fun <T : ViewBinding> viewBinding(
        crossinline bindingInflater: (LayoutInflater) -> T) =
        lazy(LazyThreadSafetyMode.NONE) {
            bindingInflater.invoke(layoutInflater)
        }
}