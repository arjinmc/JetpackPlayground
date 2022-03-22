package com.arjinmc.jetpackplayground.ui.compose.basic

import android.content.Context
import android.os.Bundle
import android.widget.PopupWindow
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.ui.compose.widget.CommonHeader
import kotlinx.coroutines.launch

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

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CommonHeader(
                titleStr = "Scaffold",
                onLeftClick = onLeftClick
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show snackbar") },
                onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState
                            .showSnackbar("Snackbar")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(backgroundColor = Color.LightGray) {
                Image(
                    painter = painterResource(R.drawable.ic_baseline_star),
                    contentDescription = null,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    ) { paddingValues ->

        val state = rememberScaffoldState()

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

            var showDialogState by rememberSaveable { mutableStateOf(false) }
            if (showDialogState) {
                FunctionalityNotAvailablePopup { showDialogState = false }
            }

            Button(onClick = { showDialogState = true }) {
                Text(text = "Show Dialog")
            }


            Scaffold {
                repeat(5) {
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
}

@Composable
private fun FunctionalityNotAvailablePopup(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            Text(
                text = "Test for Dialog",
                style = MaterialTheme.typography.body2
            )
        },
        dismissButton ={
            TextButton(onClick = onDismiss) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Confirm")
            }
        }
    )
}