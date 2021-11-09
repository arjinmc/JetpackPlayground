package com.arjinmc.jetpackplayground.util

import android.content.Context
import android.content.Intent

/**
 *
 * Created by Eminem Lo on 11/9/21
 * email: arjinmc@hotmail.com
 */
object IntentUtil {

    fun startActivity(context: Context?, clz: Class<*>?) {
        if (context == null || clz == null) {
            return
        }
        context.startActivity(Intent(context, clz))
    }
}