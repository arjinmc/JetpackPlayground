package com.arjinmc.jetpackplayground.ui.compose.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by Eminem Lo on 3/28/22
 * email: arjinmc@hotmail.com
 */
enum class NavigationRoute(var code: Int, var routeName: String, var icon: ImageVector) {
    HOME(0, "HOME", Icons.Filled.Home),
    CONTACT(1, "CONTACT", Icons.Filled.Contacts),
    MESSAGE(2, "MESSAGE", Icons.Filled.Message)
}