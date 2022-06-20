package com.arjinmc.jetpackplayground.ui.compose.basic

import android.Manifest
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arjinmc.jetpackplayground.ui.compose.widget.CommonHeader
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

/**
 * Created by Eminem Lo on 6/20/22
 * email: arjinmc@hotmail.com
 */
class ComposePermissionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ComponentPermissionPage { finish() } }

    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    private fun ComponentPermissionPage(onLeftClick: () -> Unit) {

        val cameraPermission = rememberPermissionState(Manifest.permission.CAMERA)
        val cameraLauncher: ManagedActivityResultLauncher<Void?, Bitmap?> =
            rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
                Log.e("cameraLauncher", "take picture result bitmap")
            }

        Scaffold(topBar = {
            CommonHeader(
                titleStr = "Compose ConstrainLayout",
                onLeftClick = onLeftClick
            )
        }) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = "Permission Status:${cameraPermission.hasPermission}")
                Button(onClick = {
                    if (!cameraPermission.hasPermission) {
                        cameraPermission.launchPermissionRequest()
                        return@Button
                    }
                    cameraLauncher.launch()
                }) {
                    Text(text = "Open Camera")
                }
            }
        }
    }

}