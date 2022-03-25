package com.arjinmc.jetpackplayground.ui.compose.viewpager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arjinmc.jetpackplayground.ui.compose.tabs.ComposeTabs
import com.arjinmc.jetpackplayground.ui.compose.tabs.EatPart
import com.arjinmc.jetpackplayground.ui.compose.tabs.PlayPart
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

/**
 * Created by Eminem Lo on 3/24/22
 * email: arjinmc@hotmail.com
 */
class ComposeViewPager2Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ViewPagerPage(this) { finish() }
            Procedures()
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Procedures() {

    val tabData = listOf(
        ComposeTabs.EAT,
        ComposeTabs.PLAY
    )

//    val pagerState = rememberPagerState(
//        pageCount = tabData.size,
//        initialOffscreenLimit = 2,
//        infiniteLoop = true,
//        initialPage = 0,
//    )

    val pagerState = rememberPagerState(initialPage = 0)
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    Column {

        // TAB
        TabRow(
            selectedTabIndex = tabIndex,
            modifier = Modifier.padding(top = 20.dp)
        ) {
            tabData.forEachIndexed { index, tab ->
                Tab(selected = tabIndex == index, onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }, text = {
                    Text(text = tab.name)
                })
            }
        }

        // PAGER
        HorizontalPager(
            count = ComposeTabs.values().size,
            state = pagerState
        ) { index ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (tabData[index]) {
                    ComposeTabs.EAT -> EatPart()
                    ComposeTabs.PLAY -> PlayPart()
                }
            }
        }
    }
}