package com.example.videoslist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.videoslist.data.Video

class VideoViewModel : ViewModel() {

    private val _videoList = MutableLiveData<List<Video>>()
    val videoList: LiveData<List<Video>> = _videoList

    private val _selectedVideo = MutableLiveData<Video?>()
    val selectedVideo: LiveData<Video?> = _selectedVideo

    init {
        // Sample data
        _videoList.value = listOf(
            Video("1", "For Bigger Meltdowns", "00:15", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerMeltdowns.jpg", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"),
            Video("2", "Big Buck Bunny", "09:56", "https://m.media-amazon.com/images/M/MV5BOTI5ZTNkYWQtNDg2Mi00MTBmLTliMGItNTI5YWI5OTZkM2Y2XkEyXkFqcGdeQXVyNzU1NzE3NTg@._V1_QL75_UX500_CR0,47,500,281_.jpg", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        )
    }

    fun selectVideo(video: Video) {
        _selectedVideo.value = video
    }
}