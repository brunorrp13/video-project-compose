package com.example.videoslist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.videoslist.data.Video
import com.example.videoslist.presentation.viewmodel.VideoViewModel

@Composable
fun VideoListComposable(
    viewModel: VideoViewModel,
    onVideoSelected: (Video) -> Unit
) {
    // State to hold the list of videos
    val videos by viewModel.videoList.observeAsState(emptyList())

    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        // Title
        Text(
            text = "Videos",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(videos) { video ->
                VideoListItem(video, onVideoSelected)
            }
        }
    }
}

@Composable
fun VideoListItem(video: Video, onClick: (Video) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick(video) }
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(video.thumbnailUrl),
                contentDescription = video.title,
                modifier = Modifier.size(100.dp)
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = video.title, style = MaterialTheme.typography.headlineMedium)
                Text(text = video.duration, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}