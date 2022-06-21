package com.arjinmc.jetpackplayground.ui.compose.basic

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.ConfigurationCompat
import com.arjinmc.jetpackplayground.ui.compose.tabs.ComposeTabs
import com.arjinmc.jetpackplayground.ui.compose.tabs.EatPart
import com.arjinmc.jetpackplayground.ui.compose.tabs.PlayPart
import com.arjinmc.jetpackplayground.ui.compose.widget.ComposeBaseHeader
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

/**
 * Created by Eminem Lo on 3/24/22
 * email: arjinmc@hotmail.com
 */
class ComposeViewPagerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewPagerPage(this) { finish() }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewPagerPage(context: Context, onLeftClick: () -> Unit) {
    Scaffold(
        topBar = {
            ComposeBaseHeader(
                context = context,
                titleStr = "ViewPager",
                onLeftClick = onLeftClick
            )
        },
    ) {
        val pagerState = rememberPagerState(initialPage = 0)
        //need mark the index as pagerState.currentPage
        val tabIndex = pagerState.currentPage
        Column {
            ScrollableTabLayout(
                tabIndex,
                pageState = pagerState
            )
            ViewPageLayout(pagerState)
        }
    }
}

@Composable
fun ViewPagerPageHome() {
    Text(text = "This is Home", color = Color.Blue, fontSize = 20.sp)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ScrollableTabLayout(
    tabIndex: Int,
    pageState: PagerState
) {

    val coroutineScope = rememberCoroutineScope()

    ScrollableTabRow(selectedTabIndex = tabIndex) {

        ComposeTabs.values().map { it.name }.forEachIndexed { index, title ->
            val selected = tabIndex == index

            var borderModifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            if (selected) {
                borderModifier =
                    Modifier
                        .border(BorderStroke(2.dp, Color.White), RoundedCornerShape(10.dp))
                        .then(borderModifier)
            }

            Tab(
                modifier = Modifier
                    .padding(6.dp)
                    .defaultMinSize(100.dp),
                selected = selected,
                onClick = {
                    coroutineScope.launch {
                        pageState.animateScrollToPage(index)
                    }
                }
            ) {
                Box(modifier = borderModifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White,
                        text = title.uppercase(
                            ConfigurationCompat.getLocales(LocalConfiguration.current)[0]!!
                        )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewPageLayout(pageState: PagerState) {
    HorizontalPager(
        count = ComposeTabs.values().size,
        state = pageState
    ) { index ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (index) {
                ComposeTabs.HOME.ordinal -> {
                    ViewPagerPageHome()
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