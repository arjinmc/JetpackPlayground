package com.arjinmc.jetpackplayground.ui.compose.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arjinmc.jetpackplayground.ui.compose.route.DrawerRoute
import kotlinx.coroutines.launch

/**
 * Created by Eminem Lo on 3/22/22
 * email: arjinmc@hotmail.com
 */
class ComposeDrawerMenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { DrawerMenuMain() }
    }

    @Composable
    fun DrawerMenuMain() {

        val navController = rememberNavController()
        val drawerState = rememberDrawerState(DrawerValue.Closed)

        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                DrawerMenu(drawerState = drawerState, navController = navController)
            }) {

            NavHost(navController = navController, startDestination = DrawerRoute.HOME_ROUTE) {

                composable(DrawerRoute.HOME_ROUTE) {
                    DrawerHomePage(drawerState)
                }
                composable(DrawerRoute.TAB_ROUTE) {
                    DrawerTabPage(drawerState)
                }
            }
        }

    }

    @Composable
    fun DrawerMenu(drawerState: DrawerState, navController: NavHostController) {
        val listState = rememberLazyListState()
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxWidth()
        ) {
            val listData = mutableListOf(DrawerRoute.HOME_ROUTE, DrawerRoute.TAB_ROUTE)
            items(listData) { itemData ->
                ListViewItem(
                    data = itemData,
                    drawerState = drawerState,
                    navController = navController
                )
            }
        }
    }

    @Composable
    fun ListViewItem(data: String, drawerState: DrawerState, navController: NavHostController) {
        val coroutineScope = rememberCoroutineScope()
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clickable {
                    navController.navigate(data) {
                        coroutineScope.launch {
                            drawerState.close()
                        }

                        //when call back to stack go to HOME_ROUTE
                        popUpTo(DrawerRoute.HOME_ROUTE)

//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                        }
//                        launchSingleTop = true
//                        restoreState = true
                    }
                }
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = data)
            }
            Icon(imageVector = Icons.Default.AdsClick, contentDescription = "")
        }
    }


    @Composable
    fun DrawerHomePage(drawerState: DrawerState) {
        val coroutineScope = rememberCoroutineScope()
        Column {
            Text(" This is Home page")
            Button({
                //open drawer
                coroutineScope.launch {
                    drawerState.open()
                }
            }) {
                Text(text = "Drawer Menu")
            }
        }
    }

    @Composable
    fun DrawerTabPage(drawerState: DrawerState) {
        val coroutineScope = rememberCoroutineScope()
        Column {
            Text(" This is Tab page")
            Button({
                //open drawer
                coroutineScope.launch {
                    drawerState.open()
                }
            }) {
                Text(text = "Drawer Menu")
            }
        }
    }

}