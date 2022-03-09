package com.arjinmc.jetpackplayground.ui.compose.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arjinmc.jetpackplayground.ui.compose.widget.ComposeBasicPage

/**
 * Created by Eminem Lo on 3/7/22
 * email: arjinmc@hotmail.com
 */
class ComposeBasicActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val pageUI = ComposeBasicPage(this) { finish() }
        }
    }

}