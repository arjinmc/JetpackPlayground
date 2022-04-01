package com.arjinmc.jetpackplayground.ui.compose.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arjinmc.jetpackplayground.ui.compose.widget.CommonHeader

/**
 * Created by Eminem Lo on 3/30/22
 * email: arjinmc@hotmail.com
 */
class ComposeCustomLayoutActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomLayoutPage {
                finish()
            }
        }
    }

    @Composable
    private fun CustomLayoutPage(onLeftClick: () -> Unit) {

        Scaffold(topBar = {
            CommonHeader(
                titleStr = "Compose Custom Layout",
                onLeftClick = onLeftClick
            )
        }) {

            CustomLayout(modifier = Modifier.padding(10.dp)) {
                repeat(20) { index ->
                        Text(
                            text = "hello $index x $index",
                            modifier = Modifier
                                //is margin
                                .padding(0.dp)
                                .border(
                                    border = BorderStroke(
                                        1.dp,
                                        Color.Green
                                    ), shape = RoundedCornerShape(5.dp)
                                )
                                //is real padding
                                .padding(vertical = 8.dp, horizontal = 12.dp),
                        )

                }

            }

        }
    }

}


/**
 * process
 * 1.measure children
 * 2.decide own size
 * 3.place children
 */
@Composable
fun CustomLayout(
    modifier: Modifier = Modifier,
    padding: Dp? = 10.dp,
    content: @Composable () -> Unit
) {
    Layout(content = content, modifier = modifier) { measurables, constraints ->
        //measure children
        val placeables = measurables.map { measurable ->
            // Measure each children
            measurable.measure(constraints)
        }

        val width = constraints.maxWidth
        val height = constraints.maxHeight

        layout(width = width, height = height) {

            var childrenX = 0
            var childrenY = 0
            placeables.forEach { placeable ->

                if (childrenX + placeable.width + padding!!.toPx().toInt() > width) {
                    childrenX = 0
                    childrenY += placeable.height + padding!!.toPx().toInt()
                    placeable.place(x = childrenX, y = childrenY)
                } else {
                    placeable.place(x = childrenX, y = childrenY)
                }
                childrenX += placeable.width + padding!!.toPx().toInt()

            }
        }

    }


}