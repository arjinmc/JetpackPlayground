package com.arjinmc.jetpackplayground.ui.compose.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import com.arjinmc.jetpackplayground.ui.compose.widget.CommonHeader

/**
 * Created by Eminem Lo on 3/30/22
 * email: arjinmc@hotmail.com
 */
class ComposeCanvasActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasPage {
                finish()
            }
        }
    }

    @Composable
    private fun CanvasPage(onLeftClick: () -> Unit) {
        Scaffold(
            topBar = { CommonHeader(titleStr = "Compose Canvas", onLeftClick = onLeftClick) }
        ) {

            Column {

                Canvas(modifier = Modifier.fillMaxSize()) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    val canvasQuadrantSize = size / 2F
                    //set draw scope like 50f x 30f
                    inset(50F, 30F) {
                        drawRect(
                            color = Color.Magenta,
                            size = canvasQuadrantSize
                        )
                    }

                    drawCircle(Color.Yellow, 200f)

                    rotate(degrees = 30f) {
                        drawRect(
                            Color.Green,
                            Offset(30f, 30f),
                            Size(200f, 200f),
                            0.8f,
                            Stroke(width = 10f)
                        )
                    }


                    drawLine(
                        start = Offset(x = canvasWidth, y = 0f),
                        end = Offset(x = 0f, y = canvasHeight),
                        color = Color.Blue
                    )
                }
            }
        }

    }

}