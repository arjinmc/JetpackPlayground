package com.arjinmc.jetpackplayground.ui.compose.basic

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arjinmc.jetpackplayground.ui.compose.widget.CommonHeader
import kotlin.math.roundToInt

/**
 * Created by Eminem Lo on 3/31/22
 * email: arjinmc@hotmail.com
 * link: https://developer.android.com/jetpack/compose/gestures
 */
class ComposeGestureActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GesturePage {
                finish()
            }
        }
    }

    @Composable
    private fun GesturePage(onLeftClick: () -> Unit) {
        Scaffold(topBar = {
            CommonHeader(
                onLeftClick = onLeftClick,
                titleStr = "Compose Gesture"
            )
        }) {

            Column (Modifier.padding(10.dp)){

                val count = remember { mutableStateOf(0) }
                // content that you want to make clickable
                Text(
                    text = count.value.toString(),
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(20.dp)
                        .clickable { count.value += 1 }
                )

                Text(text = "Test click",
                    fontSize = 40.sp,
                    modifier = Modifier.pointerInput(Unit) {
                        detectTapGestures(
                            onPress = { /* Called when the gesture starts */
                                Log.i("onClick", "onPress")
                            },
                            onDoubleTap = { /* Called on Double Tap */
                                Log.i("onClick", "onDoubleTap")
                            },
                            onLongPress = { /* Called on Long Press */
                                Log.i("onClick", "onLongPress")
                            },
                            onTap = { /* Called on Tap */
                                Log.i("onClick", "onTap")
                            }
                        )
                    })

//                ScrollableSample1()
//                ScrollableSample2()
                DragSample1()
//                DragSample2()
                SwipeableSample()
                TransformableSample()
            }

        }
    }

    @Composable
    private fun ScrollableSample1() {
        // actual composable state
        var offset by remember { mutableStateOf(0f) }
        Box(
            Modifier
                .size(150.dp)
                .scrollable(
                    orientation = Orientation.Vertical,
                    // Scrollable state: describes how to consume
                    // scrolling delta and update offset
                    state = rememberScrollableState { delta ->
                        offset += delta
                        delta
                    }
                )
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(offset.toString())
        }
    }

    @Composable
    private fun ScrollableSample2(){
        val gradient = Brush.verticalGradient(0f to Color.Gray, 1000f to Color.White)
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .verticalScroll(rememberScrollState())
                .padding(32.dp)
        ) {
            Column {
                repeat(6) {
                    Box(
                        modifier = Modifier
                            .height(128.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            "Scroll here",
                            modifier = Modifier
                                .border(12.dp, Color.DarkGray)
                                .background(brush = gradient)
                                .padding(24.dp)
                                .height(150.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun DragSample1(){
        var offsetX by remember { mutableStateOf(0f) }
        Text(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        offsetX += delta
                    }
                ),
            text = "Drag me!"
        )
    }

    @Composable
    private fun DragSample2(){
        Box(modifier = Modifier.fillMaxSize()) {
            var offsetX by remember { mutableStateOf(0f) }
            var offsetY by remember { mutableStateOf(0f) }

            Box(
                Modifier
                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                    .background(Color.Blue)
                    .size(50.dp)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consumeAllChanges()
                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
                        }
                    }
            )
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun SwipeableSample() {
        val width = 96.dp
        val squareSize = 48.dp

        val swipeableState = rememberSwipeableState(0)
        val sizePx = with(LocalDensity.current) { squareSize.toPx() }
        val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states

        Box(
            modifier = Modifier
                .width(width)
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(0.3f) },
                    orientation = Orientation.Horizontal
                )
                .background(Color.LightGray)
        ) {
            Box(
                Modifier
                    .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                    .size(squareSize)
                    .background(Color.DarkGray)
            )
        }
    }

    @Composable
    fun TransformableSample() {
        // set up all transformation states
        var scale by remember { mutableStateOf(1f) }
        var rotation by remember { mutableStateOf(0f) }
        var offset by remember { mutableStateOf(Offset.Zero) }
        val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
            scale *= zoomChange
            rotation += rotationChange
            offset += offsetChange
        }
        Box(
            Modifier
                // apply other transformations like rotation and zoom
                // on the pizza slice emoji
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    rotationZ = rotation,
                    translationX = offset.x,
                    translationY = offset.y
                )
                // add transformable to listen to multitouch transformation events
                // after offset
                .transformable(state = state)
                .background(Color.Blue)
                .size(400.dp)
        )
    }
}