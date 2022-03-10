package com.arjinmc.jetpackplayground.ui.compose.basic

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.arjinmc.jetpackplayground.util.ToastUtil

/**
 * Created by Eminem Lo on 3/10/22
 * email: arjinmc@hotmail.com
 */
class ComposeLoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginPage()
        }
    }

    @Composable
    fun LoginPage() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(48.dp))

            Column(
                Modifier.border(1.dp, Color.Green, RoundedCornerShape(10.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                var username by remember { mutableStateOf("") }
                var isUserNameError = false
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    maxLines = 1,
                    textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
                    isError = isUserNameError,
                    modifier = Modifier
                        .padding(20.dp)
                        .onFocusChanged {
                            if (!it.isFocused) {
                                isUserNameError =
                                    username.isNullOrEmpty() || username.isDigitsOnly()
                                if (isUserNameError) {
                                    ToastUtil.showShort(
                                        this@ComposeLoginActivity,
                                        "username cannot be empty or only digits."
                                    )
                                }
                            }
                        }
                )

                var pwd by remember { mutableStateOf("") }

                TextField(
                    value = pwd,
                    onValueChange = { pwd = it },
                    label = { Text("Password") },
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Button(onClick = {
                    if (username.isNullOrBlank() || pwd.isNullOrBlank()) {
                        ToastUtil.showShort(this@ComposeLoginActivity, "Please fill the blanks!")
                        return@Button
                    }
                    Log.e("tag", "login:$username,$pwd")
                    ToastUtil.showShort(this@ComposeLoginActivity, "login:$username,$pwd")
                }, modifier = Modifier.padding(20.dp)) {
                    Text(text = "Login")
                }

                Button(
                    onClick = { Log.e("tag", "login:$username,$pwd") },
                    modifier = Modifier
                        .padding(bottom = 20.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth()
                ) {
                    Image(Icons.Default.Login, null, Modifier.padding(end = 8.dp))
                    Text(text = "Login")
                }
            }
        }
    }
}