package com.arjinmc.jetpackplayground.basic

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Created by Eminem Lo on 12/8/21
 * mail: arjinmc@hotmail.com
 */
abstract class BasicFragment : Fragment(), BasicActivityInterface {

    inline fun <T : ViewBinding> viewBinding(
        crossinline bindingInflater: (LayoutInflater) -> T
    ) =
        lazy(LazyThreadSafetyMode.NONE) {
            bindingInflater.invoke(layoutInflater)
        }
}