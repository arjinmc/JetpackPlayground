package com.arjinmc.jetpackplayground.ui.compose.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.arjinmc.jetpackplayground.ui.compose.widget.CommonHeader

/**
 * Created by Eminem Lo on 3/30/22
 * email: arjinmc@hotmail.com
 */
class ComposeConstrainLayoutActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {  ConstrainLayoutPage {
            finish()
        }}
    }

    @Composable
    private fun ConstrainLayoutPage(onLeftClick:()->Unit){

        Scaffold(topBar = { CommonHeader(titleStr = "Compose ConstrainLayout", onLeftClick = onLeftClick)}) {

        }
    }
}