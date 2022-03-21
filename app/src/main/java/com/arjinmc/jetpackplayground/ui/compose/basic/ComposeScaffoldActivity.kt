package com.arjinmc.jetpackplayground.ui.compose.basic

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.ui.compose.widget.CommonHeader

/**
 * Created by Eminem Lo on 3/21/22
 * email: arjinmc@hotmail.com
 */
class ComposeScaffoldActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldPage(context = this) { finish() }
        }
    }
}

@Composable
fun ScaffoldPage(context: Context, onLeftClick: () -> Unit) {

    Scaffold(topBar = {
        CommonHeader(
            titleStr = "Scaffold",
            onLeftClick = onLeftClick
        )
    }) { paddingValues ->

        val state  = rememberScaffoldState()

        Column {
            Text(
                "Hello Compose",
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp)
            )
            Text(
                "Hello Compose ".repeat(50),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1
            )

            repeat(20){
                Text(
                    "Hello, do you know me? I'm arjinmc from Shenzhen, China",
                    maxLines = 2,
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.overline,
                    color = colorResource(R.color.teal_700),
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.clickable {

                    }
                )
            }

        }
    }
}