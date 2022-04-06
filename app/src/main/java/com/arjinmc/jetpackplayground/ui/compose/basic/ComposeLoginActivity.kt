package com.arjinmc.jetpackplayground.ui.compose.basic

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.arjinmc.jetpackplayground.R
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

                PasswordTextField(text = pwd, onTextChanged = { pwd = it }, modifier = Modifier.padding(start = 20.dp, end = 20.dp))

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

    @Composable
    fun PasswordTextField(
        text: String,
        modifier: Modifier = Modifier,
        semanticContentDescription: String = "",
        labelText: String = "",
        validateStrengthPassword: Boolean = false,
        hasError: Boolean = false,
        onHasStrongPassword: (isStrong: Boolean) -> Unit = {},
        onTextChanged: (text: String) -> Unit,
    ) {
        val focusManager = LocalFocusManager.current
        val showPassword = remember { mutableStateOf(false) }

        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.teal_700))
                    .fillMaxWidth()
                    .semantics { contentDescription = semanticContentDescription },
                value = text,
                onValueChange = onTextChanged,
                placeholder = {
                    Text(
                        text = labelText,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                singleLine = true,
                isError = hasError,
                visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val (icon, iconColor) = if (showPassword.value) {
                        Pair(
                            Icons.Filled.Visibility,
                            colorResource(id = R.color.white)
                        )
                    } else {
                        Pair(Icons.Filled.VisibilityOff, colorResource(id = R.color.white))
                    }

                    IconButton(onClick = { showPassword.value = !showPassword.value }) {
                        Icon(
                            icon,
                            contentDescription = "Visibility",
                            tint = iconColor
                        )
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    textColor = Color.White,
                    cursorColor = Color.White,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (validateStrengthPassword && !text.isNullOrEmpty()) {
                val strengthPasswordType = strengthChecker(text)
                if (strengthPasswordType == StrengthPasswordTypes.STRONG) {
                    onHasStrongPassword(true)
                } else {
                    onHasStrongPassword(false)
                }
                Text(
                    modifier = Modifier.semantics {
                        contentDescription = "StrengthPasswordMessage"
                    },
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.White,
                                fontSize = 10.sp,
                            )
                        ) {
                            append("Password Level")
                            withStyle(style = SpanStyle(color = Color.Yellow)) {
                                when (strengthPasswordType) {
                                    StrengthPasswordTypes.STRONG ->
                                        append(" Level Strong")
                                    StrengthPasswordTypes.WEAK ->
                                        append(" Level Weak")
                                }
                            }
                        }
                    }
                )
            }
        }
    }

    private fun strengthChecker(password: String): StrengthPasswordTypes =
        when {
            "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{8,})".toRegex()
                .containsMatchIn(password) -> StrengthPasswordTypes.STRONG
            else -> StrengthPasswordTypes.WEAK
        }

    enum class StrengthPasswordTypes {
        STRONG,
        WEAK
    }
}