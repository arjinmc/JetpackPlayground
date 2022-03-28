package com.arjinmc.jetpackplayground.ui.compose.basic

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.ui.compose.route.NavigationRoute
import com.arjinmc.jetpackplayground.ui.compose.widget.ComposeBaseHeader

/**
 * Created by Eminem Lo on 3/28/22
 * email:arjinmc@hotmail.com
 */
class ComposeNavigationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationPage(context = this) {
                finish()
            }
        }
    }

    @Composable
    fun NavigationPage(context: Context, onLeftClick: () -> Unit) {

        val navController = rememberNavController()

        Scaffold(topBar = {
            ComposeBaseHeader(
                context = context,
                titleStr = "ViewPager",
                onLeftClick = onLeftClick
            )

        }, bottomBar = { NavigationBottomBar(navController) }) {

        }
    }

    @Composable
    fun NavigationBottomBar(navController: NavController) {

        val navigationDataList = NavigationRoute.values()
        var currentRoute = remember { NavigationRoute.HOME }

        BottomNavigation(
            backgroundColor = colorResource(id = R.color.purple_700),
        ) {
            navigationDataList.forEach { item ->
                BottomNavigationItem(
                    label = { Text(item.routeName, fontSize = 14.sp) },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            modifier = Modifier.padding(4.dp)
                        )
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.White.copy(0.2f),
                    selected = currentRoute == item,
                    onClick = {
//                        currentRoute = item
                        Log.e("onclick", "item:${item.routeName}")
                    }
                )
            }
        }
    }
}