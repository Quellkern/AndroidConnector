package com.example.androidconnector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androidconnector.ui.theme.AndroidConnectorTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import kotlin.time.Duration.Companion.seconds

class TextPostScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidConnectorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    TextBox()
                }
            }
        }
    }
    @Composable
    fun TextBox() {
        var modifier: Modifier = Modifier
        Box(
            modifier
                .requiredSize(height = 200.dp, width = 300.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                InputTextField()
            }

        }
    }

    @Composable
    fun InputTextField() {
        var textFieldState: String by remember {
            mutableStateOf("")
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            OutlinedTextField(
                modifier = Modifier,
                shape = AbsoluteRoundedCornerShape(8.dp),
                value = textFieldState,
                onValueChange = { textFieldState = it },
                label = { Text(text = "Enter Text") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.LightGray,
                    unfocusedTextColor = Color.LightGray,
                    disabledTextColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.DarkGray,
                    unfocusedIndicatorColor = Color.DarkGray,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent
                )
            )
            val sendButtonState = remember {
                mutableStateOf("Send")
            }
            val sendButtonBool = remember { mutableStateOf(false) }
            if (sendButtonBool.value) {
                LaunchedEffect(Unit) {
                    requestPost(textFieldState)
                    sendButtonState.value = "Sent"
                    delay(0.7.seconds)
                    sendButtonState.value = "Send"
                    sendButtonBool.value = false
                }
            }
            Button(
                modifier = Modifier,
                onClick = {
                    sendButtonBool.value = true
                },
                shape = RoundedCornerShape(size = 20.dp)
            ) {
                Text(text = sendButtonState.value)
            }
        }
    }

    private suspend fun requestPost(data: String) = coroutineScope {
        launch{
            val httpClient = OkHttpClient()
            val request = Request.Builder()
                .url("https://api.thingspeak.com/update?api_key=4FOB8L6C8KS0M0OC&field1=$data")
                .build()
            httpClient.run {
                httpClient.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        TODO("Not Yet Implemented")
                    }
                    override fun onResponse(call: Call, response: Response){}
                })
            }

        }
    }
}
