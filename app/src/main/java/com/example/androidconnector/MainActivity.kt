package com.example.androidconnector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.androidconnector.ui.theme.AndroidConnectorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidConnectorTheme {
                val sendtext = remember{
                    mutableStateOf("Send")
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(2.dp, color = Color.Green),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .requiredSize(width = 300.dp, height = 300.dp)
                            .border(3.dp, color = Color.White),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Hello", fontStyle = FontStyle.Italic)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(.5f)
                                .border(1.dp, color = Color.Blue),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(onClick = {sendtext.value = "Sent"}) {
                                Text(text = sendtext.value)
                            }
                        }

                    }
                }
            }

        }
    }
}
