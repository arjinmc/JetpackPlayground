package com.arjinmc.jetpackplayground.ui.compose.basic

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.arjinmc.jetpackplayground.ui.compose.widget.CommonHeader

/**
 * Created by Eminem Lo on 4/1/22
 * email: arjinmc@hotmail.com
 */
class ComposeAdaptiveActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdaptivePage {
                finish()
            }
        }
    }

    @Composable
    private fun AdaptivePage(onLeftClick: () -> Unit) {

        Scaffold(topBar = {
            CommonHeader(
                onLeftClick = onLeftClick,
                titleStr = "Compose Adaptive"
            )
        }) {
            val imageUrl =
                "https://img2.baidu.com/it/u=2372481164,996390177&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"

            BoxWithConstraints {
                Log.e("maxWidth", "maxWidth:$maxWidth")
                if (maxWidth < 400.dp) {
                    Column {
                        Image(
                            painter = rememberImagePainter(data = imageUrl),
                            contentDescription = null
                        )
                        Text("go method 1")
                    }
                } else {
                    Column {
                        Text("go method 2")
                        Image(
                            painter = rememberImagePainter(data = imageUrl),
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}