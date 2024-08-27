package com.example.videoslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.ViewModelProvider
import com.example.videoslist.ui.navigation.VideoApp
import com.example.videoslist.presentation.viewmodel.VideoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[VideoViewModel::class.java]
        setContent {
            MaterialTheme {
                VideoApp(viewModel)
            }
        }
    }
}
