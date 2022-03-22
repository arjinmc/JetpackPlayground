package com.arjinmc.jetpackplayground.ui.compose.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.arjinmc.jetpackplayground.ui.compose.route.DrawerNavigation
import com.arjinmc.jetpackplayground.ui.compose.route.DrawerRoute
import com.arjinmc.jetpackplayground.ui.compose.widget.NavigationIcon
import kotlinx.coroutines.launch

/**
 * Created by Eminem Lo on 3/22/22
 * email: arjinmc@hotmail.com
 */
class ComposeDrawerMenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { }
    }
}

@Composable
fun DrawerMenuMain() {

//    val currentRoute =
//        navBackStackEntry?.destination?.route ?: DrawerRoute.HOME_ROUTE
//
//    val navController = rememberNavController()
//    val navigationActions = remember(navController) {
//        DrawerNavigation(navController)
//    }
//
//    ModalDrawer(drawerContent = {
//        AppDrawer(
//            currentRoute = currentRoute,
//            navigateToHome = navigationActions.navigateToHome,
//            navigateToInterests = navigationActions.navigateToInterests,
//            closeDrawer = { coroutineScope.launch { sizeAwareDrawerState.close() } },
//            modifier = Modifier
//                .statusBarsPadding()
//                .navigationBarsPadding()
//        )
//    }) {
//
//    }
}

/**
 * drawer menu
 */
@Composable
fun AppDrawer(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToInterests: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Drawer Menu")
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
        DrawerButton(
            icon = Icons.Filled.Home,
            label = "home",
            isSelected = currentRoute == DrawerRoute.HOME_ROUTE,
            action = {
                navigateToHome()
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Icons.Filled.ListAlt,
            label = "Tabs",
            isSelected = currentRoute == DrawerRoute.TAB_ROUTE,
            action = {
                navigateToInterests()
                closeDrawer()
            }
        )
    }
}

@Composable
private fun DrawerButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colors
    val textIconColor = if (isSelected) {
        colors.primary
    } else {
        colors.onSurface.copy(alpha = 0.6f)
    }
    val backgroundColor = if (isSelected) {
        colors.primary.copy(alpha = 0.12f)
    } else {
        Color.Transparent
    }

    val surfaceModifier = modifier
        .padding(start = 8.dp, top = 8.dp, end = 8.dp)
        .fillMaxWidth()
    Surface(
        modifier = surfaceModifier,
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        TextButton(
            onClick = action,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                NavigationIcon(
                    icon = icon,
                    isSelected = isSelected,
                    contentDescription = null, // decorative
                    tintColor = textIconColor
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.body2,
                    color = textIconColor
                )
            }
        }
    }
}