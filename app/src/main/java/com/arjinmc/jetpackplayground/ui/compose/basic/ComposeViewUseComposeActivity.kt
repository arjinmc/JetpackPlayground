package com.arjinmc.jetpackplayground.ui.compose.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import com.arjinmc.jetpackplayground.R

/**
 * Created by Eminem Lo on 3/30/22
 * email: arjinmc@hotmail.com
 */
class ComposeViewUseComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_compose_view_use_compose)

        findViewById<ComposeView>(R.id.compose_content).setContent {
            UseComposePage()
        }
    }

    @Composable
    private fun UseComposePage() {

        Column {

            Text("Content from compose", color = Color.Blue)

            Row {
                Image(imageVector = Icons.Default.Home, contentDescription = "")
                Text("Home")
            }
        }
    }


}