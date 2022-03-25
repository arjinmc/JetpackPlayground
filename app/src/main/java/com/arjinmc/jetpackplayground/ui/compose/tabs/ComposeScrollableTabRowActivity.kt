package com.arjinmc.jetpackplayground.ui.compose.tabs

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.ConfigurationCompat
import com.arjinmc.jetpackplayground.ui.compose.widget.ComposeBaseHeader

/**
 * Created by Eminem Lo on 3/24/22
 * email: arjinmc@hotmail.com
 */
class ComposeScrollableTabRowActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollableTabRowPage(this) { finish() }
        }
    }
}

@Composable
fun ScrollableTabRowPage(context: Context, onLeftClick: () -> Unit) {
    var tabSelected by remember { mutableStateOf(ComposeTabs.HOME) }
    Scaffold(
        topBar = {
            ComposeBaseHeader(
                context = context,
                titleStr = "ScrollableTabRow",
                onLeftClick = onLeftClick
            )
        },
    ) {
        Column {
            ScrollableTabLayout(tabSelected = tabSelected, onPageSelected = { tabSelected = it })
            when (tabSelected.ordinal) {
                ComposeTabs.HOME.ordinal -> {
                    ScrollableTabHome()
                }
                ComposeTabs.EAT.ordinal -> {
                    EatPart()
                }
                ComposeTabs.PLAY.ordinal -> {
                    PlayPart()
                }
            }
        }
    }
}

@Composable
fun ScrollableTabHome() {
    Text(text = "This is Home", color = Color.Blue, fontSize = 20.sp)
}

@Composable
fun ScrollableTabLayout(
    tabSelected: ComposeTabs,
    onPageSelected: ((tabItem: ComposeTabs) -> Unit)
) {
    ScrollableTabRow(selectedTabIndex = tabSelected.ordinal, backgroundColor = Color.Blue,
        indicator = { positions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(positions[tabSelected.ordinal]),
                color = Color.Red,
                height = 4.dp
            )
        }) {


        ComposeTabs.values().map { it.name }.forEachIndexed { index, title ->
            val selected = index == tabSelected.ordinal

            var tabModifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)

            Tab(
                modifier = Modifier.padding(6.dp),
                selected = selected,
                onClick = { onPageSelected(ComposeTabs.values()[index]) }
            ) {
                Box(modifier = tabModifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White,
                        text = title.uppercase(
                            ConfigurationCompat.getLocales(LocalConfiguration.current)[0]
                        )
                    )
                }
            }
        }
    }
}