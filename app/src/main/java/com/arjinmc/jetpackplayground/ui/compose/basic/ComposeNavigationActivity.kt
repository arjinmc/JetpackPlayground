package com.arjinmc.jetpackplayground.ui.compose.basic

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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

        Scaffold(
            topBar = {
                ComposeBaseHeader(
                    context = context,
                    titleStr = "Navigation",
                    onLeftClick = onLeftClick
                )
            },
            bottomBar = { NavigationBottomBar(navController) },
            content = { ContentPage(navController) })
    }

    @Composable
    private fun NavigationBottomBar(navController: NavController) {
        val navigationDataList = NavigationRoute.values()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val selectedItem = navBackStackEntry?.destination?.route

        BottomNavigation(
            backgroundColor = colorResource(id = R.color.purple_700),
        ) {
            navigationDataList.forEachIndexed { index, item ->
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
                    unselectedContentColor = Color.White.copy(0.4f),
                    selected = selectedItem == item.routeName,
                    onClick = {
//                        selectedItem = item.routeName
                        navController.navigate(item.routeName) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            // Avoid multiple copies of the same destination when
                            // reselect the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    }

    @Composable
    private fun ContentPage(navController: NavHostController) {
        NavHost(navController = navController, startDestination = NavigationRoute.HOME.routeName) {
            composable(NavigationRoute.HOME.routeName) {
                ContentHome()
            }

            composable(NavigationRoute.CONTACT.routeName) {
                ContentContact()
            }

            composable(NavigationRoute.MESSAGE.routeName) {
                ContentMessage(navController)
            }

            //navigate to define and get params
            composable("messageDetail/{id}", arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )) { navBackStackEntry ->
                ContentMessageDetail(navBackStackEntry.arguments?.getInt("id"))
            }

            //navigate fot deep link
            val uri = "https://www.example.com"
            composable(
                "profile?id={id}",
                deepLinks = listOf(navDeepLink { uriPattern = "$uri/{id}" })
            ) { backStackEntry ->
                ContentMessageDetail(backStackEntry.arguments?.getInt("id"))
            }
        }
    }

    @Composable
    private fun ContentHome() {
        Text(text = "Home")
    }

    @Composable
    private fun ContentContact() {
        Text(text = "Contact")
    }

    @Composable
    private fun ContentMessage(navController: NavHostController) {
        Column {
            Text(text = "Message")
            Button(onClick = {
                //navigate with params
                navController.navigate("messageDetail/1002") {
                    popUpTo(NavigationRoute.HOME.routeName)
                }
            }) {
                Text(text = "Click to nav MessageDetail")
            }
        }
    }

    @Composable
    private fun ContentMessageDetail(msgId: Int?) {
        Text(text = "This is Message Detail.ID is:$msgId")
    }

}