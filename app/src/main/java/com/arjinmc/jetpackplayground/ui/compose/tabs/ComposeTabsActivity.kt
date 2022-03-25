package com.arjinmc.jetpackplayground.ui.compose.tabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.ConfigurationCompat
import com.arjinmc.jetpackplayground.R

/**
 * Created by Eminem Lo on 3/23/22
 * email:arjinmc@hotmail.com
 */
class ComposeTabsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabsMainPage()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TabsMainPage() {

    var tabSelected by remember { mutableStateOf(ComposeTabs.HOME) }
    BackdropScaffold(
        modifier = Modifier.alpha(1f),
        scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed),
        frontLayerScrimColor = Color.Unspecified,
        appBar = {
            HomeTabBar(tabSelected, onTabSelected = { tabSelected = it })
        }, backLayerContent = { BackLayer() }, frontLayerContent = { FrontLayer(tabSelected) }) {

    }
}

@Composable
fun FrontLayer(tabSelected: ComposeTabs) {
    when (tabSelected) {
        ComposeTabs.HOME -> Text(text = "go go go", modifier = Modifier.padding(10.dp))
        ComposeTabs.EAT -> EatPart()
        ComposeTabs.PLAY -> PlayPart()
    }

}

@Composable
fun BackLayer() {
    Column {
        repeat(20){
            Text(text = "hello", modifier = Modifier.padding(10.dp))
        }
    }
}

@Composable
fun EatPart() {
    Column {
        repeat(30) {
            Text(
                text = "Eat something",
                modifier = Modifier.padding(10.dp),
                color = Color.Red,
                fontSize = 18.sp
            )
        }
    }

}

@Composable
fun PlayPart() {
    Text(
        text = "Play a game",
        modifier = Modifier.padding(10.dp),
        color = Color.Green,
        fontSize = 18.sp
    )
}

@Composable
fun HomeTabBar(
    tabSelected: ComposeTabs,
    onTabSelected: (ComposeTabs) -> Unit,
    modifier: Modifier = Modifier
) {
    CraneTabBar(
        modifier = modifier,
    ) { tabBarModifier ->
        CraneTabs(
            modifier = tabBarModifier,
            titles = ComposeTabs.values().map { it.name },
            tabSelected = tabSelected,
            onTabSelected = { newTab -> onTabSelected(ComposeTabs.values()[newTab.ordinal]) }
        )
    }
}

@Composable
fun CraneTabBar(
    modifier: Modifier = Modifier,
    children: @Composable (Modifier) -> Unit
) {
    Row(modifier) {
        children(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
    }
}


@Composable
fun CraneTabs(
    modifier: Modifier = Modifier,
    titles: List<String>,
    tabSelected: ComposeTabs,
    onTabSelected: (ComposeTabs) -> Unit
) {
    TabRow(
        selectedTabIndex = tabSelected.ordinal,
        modifier = modifier,
        backgroundColor = colorResource(id = R.color.teal_700),
        indicator = { },
        divider = { }
    ) {
        titles.forEachIndexed { index, title ->
            val selected = index == tabSelected.ordinal

            var borderModifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            if (selected) {
                borderModifier =
                    Modifier
                        .border(BorderStroke(2.dp, Color.White), RoundedCornerShape(10.dp))
                        .then(borderModifier)
            }

            Tab(
                modifier = Modifier.padding(6.dp),
                selected = selected,
                onClick = { onTabSelected(ComposeTabs.values()[index]) }
            ) {
                Box(modifier = borderModifier.fillMaxWidth()) {
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
