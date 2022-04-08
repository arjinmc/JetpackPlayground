package com.arjinmc.jetpackplayground.ui.compose.basic

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arjinmc.jetpackplayground.ui.compose.extension.DragTarget
import com.arjinmc.jetpackplayground.ui.compose.extension.DropTarget
import com.arjinmc.jetpackplayground.ui.compose.extension.LongPressDraggable
import com.arjinmc.jetpackplayground.ui.compose.model.ComposeListData
import com.arjinmc.jetpackplayground.ui.compose.widget.CommonHeader

/**
 * Created by Eminem Lo on 4/7/22
 * email: arjinmc@hotmail.com
 */
class ComposeDragDropActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DragDropPage({ finish() }, initData())
        }
    }

    @Composable
    private fun DragDropPage(onLeftClick: () -> Unit, listData: MutableList<ComposeListData>) {

        //make sure drag target and drop target in the same layer
        LongPressDraggable {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                CommonHeader(
                    onLeftClick = onLeftClick,
                    titleStr = "Compose Drag and Drag"
                )

                val listState = rememberLazyListState()

                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {

                    items(listData.toList()) { itemData ->
                        //set which view can be dropped
                        DragTarget(modifier = Modifier.fillMaxWidth(), dataToDrop = itemData) {
                            ListViewItem(data = itemData)
                        }
                        ListDivider()
                    }
                }

                var selectedItem: ComposeListData? = ComposeListData()

                DropTarget<ComposeListData>(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(0.1f)
                ) { isInBound, data ->

                    //filter for only selected drag item
                    if (isInBound) {
                        selectedItem = data
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .border(1.dp, Color.DarkGray, RoundedCornerShape(5.dp))
                            .background(if (isInBound) Color.Red else Color.White)
                    ) {
                        Text(
                            if (selectedItem == null || selectedItem?.name.isNullOrEmpty()) {
                                "Drop content"
                            } else {
                                "${selectedItem?.name} is Dropped"
                            }
                        )
                        Log.e("go", "drop:${selectedItem?.name}")
                    }
                }
            }

        }
    }

    fun initData(): MutableList<ComposeListData> {
        return mutableListOf<ComposeListData>().apply {
            for (i in 0..5) {
                add(ComposeListData("name" + i, "content:$i x $i", System.currentTimeMillis()))
            }
        }
    }

    @Composable
    fun ListViewItem(data: ComposeListData) {

        Row(
            modifier = Modifier
                .padding(10.dp)
                .background(Color.Green)
                .fillMaxWidth()

        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = data.name ?: "")
                Text(text = data.content ?: "")
                Text(text = "" + data.timestamp)
            }
            Icon(
                imageVector = Icons.Default.AdsClick,
                contentDescription = ""
            )
        }

    }

    @Composable
    private fun ListDivider() {
        Divider(
            modifier = Modifier.padding(horizontal = 14.dp),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
        )
    }


}