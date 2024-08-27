package com.example.videoslist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.videoslist.presentation.viewmodel.VideoViewModel
import com.example.videoslist.ui.VideoDetailComposable
import com.example.videoslist.ui.VideoListComposable

@Composable
fun VideoApp(viewModel: VideoViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "videoList") {
        composable("videoList") {
            VideoListComposable(viewModel) { video ->
                viewModel.selectVideo(video)
                navController.navigate("videoDetail")
            }
        }
        composable("videoDetail") {
            val selectedVideo by viewModel.selectedVideo.observeAsState()
            selectedVideo?.let { video ->
                VideoDetailComposable(
                    video = video,
                    onBackPress = { navController.popBackStack() } // Handle back press
                )
            }
        }
    }
}