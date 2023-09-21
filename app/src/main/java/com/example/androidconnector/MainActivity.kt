package com.example.androidconnector

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
                    Box(modifier = Modifier.border(1.dp, Color.White).requiredSize(height = 55.dp, width = 300.dp)){
                        Button(
                            modifier = Modifier
                                .align(Alignment.Center),
                            onClick = {
                                intent = Intent(applicationContext,TextPostScreen::class.java)
                                startActivity(intent)
                            }
                        ) {
                            Text(text = "Auth Screen Empty Screen")
                        }
                    }
                }
            }
        }
    }
}