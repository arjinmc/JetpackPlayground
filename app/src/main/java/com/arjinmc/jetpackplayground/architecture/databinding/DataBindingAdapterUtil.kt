package com.arjinmc.jetpackplayground.architecture.databinding

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * Created by Eminem Lo on 1/5/22
 * email: arjinmc@hotmail.com
 */
object DataBindingAdapterUtil {

    @JvmStatic
    @BindingAdapter("app:shown")
    fun shown(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("app:setData")
    internal fun TextView.setData(data: String?) {
        text = data
    }
}