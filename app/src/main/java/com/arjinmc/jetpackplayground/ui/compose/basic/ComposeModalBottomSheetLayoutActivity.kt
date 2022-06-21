package com.arjinmc.jetpackplayground.ui.compose.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.arjinmc.jetpackplayground.ui.compose.widget.CommonHeader
import kotlinx.coroutines.launch

/**
 * Created by Eminem Lo on 6/21/22
 * email: arjinmc@hotmail.com
 */
class ComposeModalBottomSheetLayoutActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModalBottomSheetLayoutPage() {
                finish()
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun ModalBottomSheetLayoutPage(onLeftClick: () -> Unit) {

        Scaffold(topBar = {
            CommonHeader(
                onLeftClick = onLeftClick,
                titleStr = "Compose ModalBottomSheetLayout"
            )
        }) {
            var skipHalfExpanded by remember { mutableStateOf(false) }
            val state = rememberModalBottomSheetState(
                initialValue = ModalBottomSheetValue.Hidden,
                skipHalfExpanded = skipHalfExpanded
            )
            val scope = rememberCoroutineScope()
            ModalBottomSheetLayout(
                sheetState = state,
                sheetContent = {
                    LazyColumn {
                        items(50) {
                            ListItem(
                                text = { Text("Item $it") },
                                icon = {
                                    Icon(
                                        Icons.Default.Favorite,
                                        contentDescription = "Localized description"
                                    )
                                }
                            )
                        }
                    }
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        Modifier.toggleable(
                            value = skipHalfExpanded,
                            role = Role.Checkbox,
                            onValueChange = { checked -> skipHalfExpanded = checked }
                        )
                    ) {
                        Checkbox(checked = skipHalfExpanded, onCheckedChange = null)
                        Spacer(Modifier.width(16.dp))
                        Text("Skip Half Expanded State")
                    }
                    Spacer(Modifier.height(20.dp))
                    Button(onClick = { scope.launch { state.show() } }) {
                        Text("Click to show sheet")
                    }
                }
            }
        }
    }
}