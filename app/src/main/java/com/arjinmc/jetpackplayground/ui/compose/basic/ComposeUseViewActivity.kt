package com.arjinmc.jetpackplayground.ui.compose.basic

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.ui.compose.widget.CommonHeader
import com.arjinmc.jetpackplayground.ui.compose.widget.ComposeBaseHeader

/**
 * Created by Eminem Lo on 3/30/22
 * email: arjinmc@hotmail.com
 */
class ComposeUseViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UseViewPage(this) {
                finish()
            }
        }
    }

    @Composable
    private fun UseViewPage(context: Context, onLeftClick: () -> Unit?) {

        Column {

            CommonHeader(
                titleStr = "Compose use View",
                onLeftClick = onLeftClick
            )
            Text("Include android view below")

            AndroidView(factory = { context ->

                LinearLayout(context).apply {
                    orientation = LinearLayout.VERTICAL
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    setPadding(10, 10, 10, 10)
                    gravity = Gravity.CENTER
                    addView(TextView(context).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        text = "Code in Android View"
                        textSize = 32f
                    })
                }
            })

            AndroidView(
                factory = { context: Context ->
                    val view =
                        LayoutInflater.from(context).inflate(R.layout.layout_compose_use_view, null)
                    val tvName = view.findViewById<TextView>(R.id.tv_1)
                    tvName.text = "Layout from xml"
                    view
                })

            Box(modifier = Modifier.padding(10.dp)) {
                Text("Sample done", color = Color.Red)
            }
        }

    }


}