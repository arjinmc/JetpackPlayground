package com.arjinmc.jetpackplayground.ui.compose.basic

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arjinmc.jetpackplayground.ui.compose.model.ComposeListData
import com.arjinmc.jetpackplayground.ui.compose.widget.ComposeBaseHeader
import com.arjinmc.jetpackplayground.util.ToastUtil

/**
 * Created by Eminem Lo on 3/10/22
 * email: arjinmc@hotmail.com
 */
class ComposeListViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListViewPage(this, { finish() }, initData())
        }
    }

    fun initData(): MutableList<ComposeListData> {
        return mutableListOf<ComposeListData>().apply {
            for (i in 0..20) {
                add(ComposeListData("name" + i, "content:$i x $i", System.currentTimeMillis()))
            }
        }
    }

    @Composable
    fun ListViewPage(
        context: Context,
        onLeftClick: () -> Unit?,
        listData: MutableList<ComposeListData>
    ) {
        Column {
            ComposeBaseHeader(
                context = context,
                titleStr = "Compose List",
                onLeftClick = onLeftClick
            )

            Scaffold(modifier = Modifier.weight(1f)) {
                //use this type enable to scroll
                val listState = rememberLazyListState()
                LazyColumn(state = listState) {
                    Modifier
                        .padding(10.dp, 10.dp)
                        .fillMaxWidth()
                    items(listData.toList()) { itemData ->
                        ListViewItem1(context = context, data = itemData)
                        ListDivider1()
                    }
                }
            }

            Column {
                val listState2 = rememberLazyListState()
                LazyRow(state = listState2, modifier = Modifier.fillMaxHeight(0.1f)) {
                    items(listData.toList()) { itemData ->
                        ListViewItem2(context = context, data = itemData)
                        Divider(
                            modifier = Modifier
                                .fillParentMaxHeight()
                                .width(1.dp),
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun ListViewItem1(context: Context, data: ComposeListData) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clickable {
                    ToastUtil.showShort(context, "click item :${data.name}")
                }
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = data.name ?: "")
                Text(text = data.content ?: "")
                Text(text = "" + data.timestamp)
            }
            Icon(imageVector = Icons.Default.AdsClick, contentDescription = "")
        }
    }

    @Composable
    fun ListViewItem2(context: Context, data: ComposeListData) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .clickable {
                    ToastUtil.showShort(context, "click item2 :${data.name}")
                }
        ) {
            Column() {
                Text(text = data.name ?: "")
                Text(text = data.content ?: "")
                Text(text = "" + data.timestamp)
            }
            Icon(imageVector = Icons.Default.AdsClick, contentDescription = "")
        }
    }

    @Composable
    private fun ListDivider1() {
        Divider(
            modifier = Modifier.padding(horizontal = 14.dp),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
        )
    }

}



