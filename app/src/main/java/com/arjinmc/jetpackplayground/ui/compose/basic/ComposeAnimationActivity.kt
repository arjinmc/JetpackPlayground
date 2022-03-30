package com.arjinmc.jetpackplayground.ui.compose.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.arjinmc.jetpackplayground.ui.compose.widget.CommonHeader

/**
 * Created by Eminem Lo on 3/30/22
 * email: arjinmc@hotmail.com
 * link: https://developer.android.com/jetpack/compose/animation
 */
class ComposeAnimationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationPage {
                finish()
            }
        }
    }

    @Composable
    private fun AnimationPage(onLeftClick: () -> Unit) {
        Scaffold(
            topBar = { CommonHeader(titleStr = "Compose Animation", onLeftClick = onLeftClick) }
        ) {

            Column(Modifier.padding(10.dp)) {

                var animationState by remember { mutableStateOf(false) }
                Button(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                        .requiredSize(100.dp, 40.dp), onClick = {
                        animationState = !animationState
                    }) {
                    Text(text = if (!animationState) "Show" else "Hide")
                }
                AnimationDemo1(animationState)
                AnimationDemo2(animationState)
            }
        }
    }

    @Composable
    private fun AnimationDemo1(visible: Boolean) {
        val density = LocalDensity.current
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically {
                // Slide in from 40 dp from the top.
                with(density) { -40.dp.roundToPx() }
            } + expandVertically(
                // Expand from the top.
                expandFrom = Alignment.Top
            ) + fadeIn(
                // Fade in with the initial alpha of 0.3f.
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            Text(
                "Hello Animation！",
                Modifier
                    .fillMaxWidth()
            )
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    private fun AnimationDemo2(visible: Boolean) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            // Fade in/out the background and the foreground.
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.DarkGray)) {
                Box(
                    Modifier
                        .align(Alignment.Center)
                        .animateEnterExit(
                            // Slide in/out the inner box.
                            enter = slideInVertically(),
                            exit = slideOutVertically()
                        )
                        .sizeIn(minWidth = 256.dp, minHeight = 64.dp)
                        .background(Color.Red)
                ) {
                    // Content of the notification…
                }
            }
        }
    }
}