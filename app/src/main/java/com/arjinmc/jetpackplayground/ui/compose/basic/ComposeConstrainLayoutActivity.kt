package com.arjinmc.jetpackplayground.ui.compose.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
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
//            ConstraintLayoutContent()
            DecoupledConstraintLayout()
        }
    }

    @Composable
    fun ConstraintLayoutContent() {
        ConstraintLayout {
            // Create references for the composables to constrain
            val (button, text) = createRefs()

            Button(
                onClick = { /* Do something */ },
                // Assign reference "button" to the Button composable
                // and constrain it to the top of the ConstraintLayout
                modifier = Modifier.constrainAs(button) {
                    top.linkTo(parent.top, margin = 16.dp)
                }
            ) {
                Text("Button")
            }

            // Assign reference "text" to the Text composable
            // and constrain it to the bottom of the Button composable
            Text("Text", Modifier.constrainAs(text) {
                top.linkTo(button.bottom, margin = 16.dp)
            })
        }
    }

    @Composable
    fun DecoupledConstraintLayout() {
        BoxWithConstraints {
            val constraints = if (minWidth < 600.dp) {
                decoupledConstraints(margin = 16.dp) // Portrait constraints
            } else {
                decoupledConstraints(margin = 32.dp) // Landscape constraints
            }

            ConstraintLayout(constraints) {
                Button(
                    onClick = { /* Do something */ },
                    modifier = Modifier.layoutId("button")
                ) {
                    Text("Button")
                }

                Text("Text", Modifier.layoutId("text"))
            }
        }
    }

    private fun decoupledConstraints(margin: Dp): ConstraintSet {
        return ConstraintSet {
            val button = createRefFor("button")
            val text = createRefFor("text")

            constrain(button) {
                top.linkTo(parent.top, margin = margin)
            }
            constrain(text) {
                top.linkTo(button.bottom, margin)
            }
        }
    }
}