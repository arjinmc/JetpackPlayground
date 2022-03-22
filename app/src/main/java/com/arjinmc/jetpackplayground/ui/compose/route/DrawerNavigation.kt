package com.arjinmc.jetpackplayground.ui.compose.route

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * Created by Eminem Lo on 3/22/22
 * email: arjinmc@hotmail.com
 */
class DrawerNavigation(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(DrawerRoute.HOME_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToTabs: () -> Unit = {
        navController.navigate(DrawerRoute.TAB_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}