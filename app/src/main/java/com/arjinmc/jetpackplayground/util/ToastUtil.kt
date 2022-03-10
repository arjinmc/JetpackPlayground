package com.arjinmc.jetpackplayground.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * Created by Eminem Lo on 3/10/22
 * email: arjinmc@hotmail.com
 */
object ToastUtil {

    fun showShort(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showShort(context: Context, @StringRes msgRes: Int) {
        Toast.makeText(context, context.getString(msgRes), Toast.LENGTH_SHORT).show()
    }
}