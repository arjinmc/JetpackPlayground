package com.arjinmc.jetpackplayground.ui.compose.basic

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arjinmc.jetpackplayground.ui.compose.model.ComposeListData
import com.arjinmc.jetpackplayground.ui.compose.widget.ComposeBaseHeader

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
            for (i in 0..10) {
                add(ComposeListData("name" + i, "content:$i x $i",System.currentTimeMillis()))
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

            Column(Modifier.padding(10.dp, 10.dp).fillMaxWidth()) {
                listData.forEach { itemData ->
                    ListViewItem1(data = itemData)
                    ListDivider()
                }
            }

        }
    }

    @Composable
    fun ListViewItem1(data: ComposeListData) {
        Row (Modifier.padding(10.dp).fillMaxWidth()){
            Column {
                Text(text = data.name ?: "")
                Text(text = data.content ?: "")
                Text(text = "" + data.timestamp)
            }
            Icon(imageVector = Icons.Default.AdsClick, contentDescription = "")
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



