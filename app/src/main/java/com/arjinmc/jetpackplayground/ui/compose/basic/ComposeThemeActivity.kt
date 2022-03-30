package com.arjinmc.jetpackplayground.ui.compose.basic

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arjinmc.jetpackplayground.ui.compose.theme.AThemeShapes
import com.arjinmc.jetpackplayground.ui.compose.theme.JetnewsTheme
import com.arjinmc.jetpackplayground.ui.compose.widget.CommonHeader

/**
 * Created by Eminem Lo on 3/30/22
 * email: arjinmc@hotmail.com
 */
class ComposeThemeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetnewsTheme {
                ThemePage { finish() }
            }
        }
    }

    @Composable
    private fun ThemePage(onLeftClick: () -> Unit) {
        Scaffold(
            topBar = {
                CommonHeader(
                    titleStr = "Compose Theme",
                    onLeftClick = onLeftClick
                )
            }
        ) {

            Column {
                Text("Test Theme")
                var checkState by remember { mutableStateOf(false) }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = checkState, onCheckedChange = {
                        checkState = !checkState
                        Log.e(
                            "Checkbox",
                            "checked:$checkState"
                        )
                    })
                    Text("Check")
                }

                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .border(1.dp, Color.Red, shape = AThemeShapes.large)
                ) {
                    Text(text = "Go", modifier = Modifier.padding(10.dp))
                }

                //still use JetnewsTheme not MaterialTheme
                Text("Test Head text style", style = MaterialTheme.typography.h4)

            }
        }
    }
}