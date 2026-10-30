package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ui.screens.WebViewScreen
import com.example.ui.theme.BioScienceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BioScienceTheme {
                BioScienceWebsiteApp()
            }
        }
    }
}

@Composable
fun BioScienceWebsiteApp() {
    // Renders the full, responsive, futuristic scientific website directly
    // Filling 100% of the viewport width and height with zero mobile frame or phone chrome
    WebViewScreen(modifier = Modifier.fillMaxSize())
}
