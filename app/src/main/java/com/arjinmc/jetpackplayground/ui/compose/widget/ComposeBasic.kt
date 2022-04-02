package com.arjinmc.jetpackplayground.ui.compose.widget

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arjinmc.jetpackplayground.util.ToastUtil


/**
 * Created by Eminem Lo on 3/7/22
 * email: arjinmc@hotmail.com
 */
@Composable
fun ComposeBasicPage(context: Context, onLeftClick: () -> Unit?) {
    Scaffold {
        Column {
            ComposeBaseHeader(context, "Back", onLeftClick)
            Text(
                "Hello Compose",
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp)
            )
            Text(
                "Hello Compose ".repeat(50),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                "Hello, do you know me? I'm arjinmc from Shenzhen, China",
                maxLines = 2,
                fontSize = 14.sp,
                style = MaterialTheme.typography.overline,
                color = colorResource(com.arjinmc.jetpackplayground.R.color.teal_700),
                overflow = TextOverflow.Ellipsis
            )
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append("H")
                    }
                    append("ello ")

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                        append("W")
                    }
                    append("orld")
                }
            )

            SelectionContainer(Modifier.background(Color.Green)) {
                Column {
                    Text("This text is selectable")
                    DisableSelection {
                        Text("But not this one")
                        Text("Neither this one")
                    }
                    Text("This text is selectable too")
                }

            }

            AnnotatedClickableText(context)

            var value by remember { mutableStateOf("Hello\nWorld\nInvisible") }

            TextField(
                value = value,
                onValueChange = { value = it },
                label = { Text("Enter text") },
                maxLines = 2,
                textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(20.dp)
            )
            passwordField()

            OutlinedButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Place, contentDescription = "")
            }

            var iconToggle by remember { mutableStateOf(true) }
            IconToggleButton(checked = iconToggle, onCheckedChange = { iconToggle = it }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    tint = if (iconToggle) {
                        Color.Red
                    } else {
                        Color.Gray
                    }
                )
            }

        }

    }
}

@Composable
fun ComposeBaseHeader(context: Context, titleStr: String?, onLeftClick: () -> Unit?) {

    CommonHeader(titleStr, onLeftClick) {
        IconButton(
            onClick = { Toast.makeText(context, "Setting", Toast.LENGTH_SHORT).show() }
        ) {
            Icon(Icons.Filled.Settings, null)
        }

        IconButton(
            onClick = {}
        ) {
            Icon(Icons.Filled.Done, null)
        }
    }
}

@Composable
fun AnnotatedClickableText(context: Context) {
    val annotatedText = buildAnnotatedString {
        append("Click ")

        // We attach this *URL* annotation to the following content
        // until `pop()` is called
        pushStringAnnotation(
            tag = "URL",
            annotation = "https://developer.android.com"
        )
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("here")
        }
        pop()
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = "URL", start = offset,
                end = offset
            )
                .firstOrNull()?.let { annotation ->
                    ToastUtil.showShort(context, "Clicked URL:${annotation.item}")
                }
        }
    )
}


/**
 * more visualTransformation samples
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:compose/ui/ui-text/samples/src/main/java/androidx/compose/ui/text/samples/VisualTransformationSamples.kt
 */
@Composable
fun passwordField() {
    //default text for passwordField
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Password") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}