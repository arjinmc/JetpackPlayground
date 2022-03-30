package com.arjinmc.jetpackplayground.ui.compose.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.Layout
import com.arjinmc.jetpackplayground.ui.compose.widget.CommonHeader

/**
 * Created by Eminem Lo on 3/30/22
 * email: arjinmc@hotmail.com
 */
class ComposeCustomLayoutActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            CustomLayoutPage {
                finish()
            }
        }
    }

    @Composable
    private fun CustomLayoutPage(onLeftClick:()->Unit){

        Scaffold(topBar = { CommonHeader(titleStr = "Compose Custom Layout", onLeftClick = onLeftClick)}) {


        }
    }

    /**
     * process
     * 1.measure children
     * 2.decide own size
     * 3.place children
     */
    @Composable
    private fun CustomLayout(content:@Composable ()->Unit){

//        Layout(content = content){measurables, constraints ->
//
//            layout()
//        }
    }

}