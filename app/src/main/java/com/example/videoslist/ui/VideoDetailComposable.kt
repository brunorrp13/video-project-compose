package com.example.videoslist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.videoslist.data.Video
import com.example.videoslist.util.EventLogger
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*

@Composable
fun VideoDetailComposable(
    video: Video,
    onBackPress: () -> Unit
) {
    val isPlaying = remember { mutableStateOf(true) }
    val volume = remember { mutableStateOf(1f) }
    val currentProgress = remember { mutableStateOf(0f) }
    val isFullscreen = remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Back Arrow and Title
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackPress) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = video.title, style = MaterialTheme.typography.titleLarge)
        }

        // Video Player
        Box(modifier = Modifier.weight(1f)) {
            VideoPlayerComposable(
                videoUrl = video.videoUrl,
                isPlaying = isPlaying.value,
                onPlayPause = { playing ->
                    isPlaying.value = playing
                    EventLogger.logEvent("Play/Pause clicked")
                },
                volume = volume.value,
                onVolumeChange = { newVolume ->
                    volume.value = newVolume
                    EventLogger.logEvent("Volume changed: $newVolume")
                },
                currentProgress = currentProgress.value,
                onSeek = { progress ->
                    currentProgress.value = progress
                    EventLogger.logEvent("Seek position: $progress")
                },
                isFullscreen = isFullscreen.value,
                onFullScreenToggle = {
                    isFullscreen.value = !isFullscreen.value
                    EventLogger.logEvent("FullScreen toggled")
                }
            )
        }

        // Custom Video Controls
        Row(modifier = Modifier.padding(8.dp)) {
            IconButton(onClick = {
                isPlaying.value = !isPlaying.value
                EventLogger.logEvent("Play/Pause clicked")
            }) {
                Icon(
                    imageVector = if (isPlaying.value) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                    contentDescription = "Play/Pause"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            SeekBar(
                value = currentProgress.value,
                onValueChange = {
                    currentProgress.value = it
                    EventLogger.logEvent("Seek position: $it")
                },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            SeekBar(
                value = volume.value,
                onValueChange = {
                    volume.value = it
                    EventLogger.logEvent("Volume changed: $it")
                },
                modifier = Modifier.width(100.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = {
                isFullscreen.value = !isFullscreen.value
                val text =  if (isFullscreen.value) {
                    "FullScreen toggled"
                } else {
                    "Normal screen toggled"
                }
                EventLogger.logEvent(text)
            }) {
                Icon(
                    imageVector = if (isFullscreen.value) Icons.Filled.FullscreenExit else Icons.Filled.Fullscreen,
                    contentDescription = "Fullscreen"
                )
            }
        }
    }
}
