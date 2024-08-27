package com.example.videoslist.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView


@Composable
fun VideoPlayerComposable(
    videoUrl: String,
    isPlaying: Boolean,
    onPlayPause: (Boolean) -> Unit,
    onSeek: (Float) -> Unit,
    currentProgress: Float,
    volume: Float,
    onVolumeChange: (Float) -> Unit,
    onFullScreenToggle: () -> Unit,
    isFullscreen: Boolean
) {
    val context = LocalContext.current
    val exoPlayer = remember { ExoPlayer.Builder(context).build() }
    val mediaSource = remember(videoUrl) {
        MediaItem.fromUri(videoUrl)
    }

    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }

    // Play/Pause
    LaunchedEffect(isPlaying) {
        exoPlayer.playWhenReady = isPlaying
        onPlayPause(isPlaying)
    }

    // Volume
    LaunchedEffect(volume) {
        exoPlayer.volume = volume
        onVolumeChange(volume)
    }

    // Seek
    LaunchedEffect(currentProgress) {
        exoPlayer.seekTo((currentProgress * exoPlayer.duration).toLong())
        onSeek(currentProgress)
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
                setOnClickListener {
                    onFullScreenToggle()
                }
            }
        },
        modifier = if (isFullscreen) {
            Modifier.fillMaxSize() // Fullscreen mode
        } else {
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f) // Non-fullscreen mode
        }
    )
}