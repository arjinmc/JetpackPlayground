package com.arjinmc.jetpackplayground.ui.compose.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * Created by Eminem Lo on 6/21/22
 * email: arjinmc@hotmail.com
 * source from: https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#BottomSheetScaffold(kotlin.Function1,androidx.compose.ui.Modifier,androidx.compose.material.BottomSheetScaffoldState,kotlin.Function0,kotlin.Function1,kotlin.Function0,androidx.compose.material.FabPosition,kotlin.Boolean,androidx.compose.ui.graphics.Shape,androidx.compose.ui.unit.Dp,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.unit.Dp,kotlin.Function1,kotlin.Boolean,androidx.compose.ui.graphics.Shape,androidx.compose.ui.unit.Dp,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,kotlin.Function1)
 */
class ComposeBottomSheetScaffoldActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { BottomSheetScaffoldPage() }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun BottomSheetScaffoldPage() {
        val scope = rememberCoroutineScope()
        val scaffoldState = rememberBottomSheetScaffoldState()
        BottomSheetScaffold(
            sheetContent = {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(128.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Swipe up to expand sheet")
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Sheet content")
                    Spacer(Modifier.height(20.dp))
                    Button(
                        onClick = {
                            scope.launch { scaffoldState.bottomSheetState.collapse() }
                        }
                    ) {
                        Text("Click to collapse sheet")
                    }
                }
            },
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = { Text("Bottom sheet scaffold") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Localized description")
                        }
                    }
                )
            },
            floatingActionButton = {
                var clickCount by remember { mutableStateOf(0) }
                FloatingActionButton(
                    onClick = {
                        // show snackbar as a suspend function
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Snackbar #${++clickCount}")
                        }
                    }
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = "Localized description")
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            //height for collapse status
            sheetPeekHeight = 128.dp,
            drawerContent = {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Drawer content")
                    Spacer(Modifier.height(20.dp))
                    Button(onClick = { scope.launch { scaffoldState.drawerState.close() } }) {
                        Text("Click to close drawer")
                    }
                }
            }
        ) { innerPadding ->
            LazyColumn(contentPadding = innerPadding) {
                items(100) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(Color.Gray)
                    )
                }
            }
        }
    }
}