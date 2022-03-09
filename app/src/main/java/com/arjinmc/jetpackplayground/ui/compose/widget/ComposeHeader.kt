package com.arjinmc.jetpackplayground.ui.compose.widget

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * Created by Eminem Lo on 3/7/22
 * email: arjinmc@hotmail.com
 */

@Preview
@Composable
fun CommonHeaderWithSetting(
    titleStr: String?,
    onLeftClick: () -> Unit?,
    onSettingClick: () -> Unit?
) {

    CommonHeader(titleStr, onLeftClick) {
        IconButton(
            onClick = { onSettingClick.invoke() }
        ) {
            Icon(Icons.Filled.Settings, null)
        }
    }
}

@Preview
@Composable
fun CommonHeader(
    titleStr: String? = "",
    onLeftClick: () -> Unit?,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        navigationIcon = {
            if (!titleStr.isNullOrEmpty()) {
                IconButton(
                    onClick = { onLeftClick.invoke() }
                ) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            }
        },
        title = {
            Text(titleStr ?: "")
        }, actions = actions
    )
}
