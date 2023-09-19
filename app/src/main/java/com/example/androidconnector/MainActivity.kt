package com.example.androidconnector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androidconnector.ui.theme.AndroidConnectorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidConnectorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TextBox()
                }
            }
        }
    }

    @Composable
    fun TextBox() {
        val modifier: Modifier = Modifier
        Box(
            modifier
                .requiredSize(height = 200.dp, width = 300.dp)
//                .border(1.dp, Color.White)

        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
//                    .border(1.dp, Color.Red)
            ){
                InputTextField()
            }
            Row (
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 17.dp, end = 10.dp),
            ){
                WriteReqBox()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun InputTextField() {
        var textFieldState: String by remember {
            mutableStateOf("")
        }
        TextField(
            modifier = Modifier,
            shape = AbsoluteRoundedCornerShape(8.dp),
            value = textFieldState,
            onValueChange = {
                textFieldState = it
            },
            label = { Text(text = "Enter Text") },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.LightGray,
                disabledTextColor = Color.Transparent,
                containerColor = Color.DarkGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }

    @Composable
    fun WriteReqBox(){
        val sendButtonState = remember {
            mutableStateOf("Send")
        }
        Button(
            modifier = Modifier,
            onClick = {
                sendButtonState.value = "Sent"

                      },
            shape = RoundedCornerShape(size = 20.dp)
        ) {
            Text(text = sendButtonState.value)
        }
    }
}