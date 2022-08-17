package com.android.tapcorder.ui.main

import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.tapcorder.util.ExtensionUtil.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private lateinit var mediaPlayer: MediaPlayer

    var isAudioPlaying = false
        private set

    private val _recordedAudioLiveData = MutableLiveData<Uri>()
    val recordedAudioLiveData = _recordedAudioLiveData

    fun playAudio(file: File) {
        Log.d(TAG, "playAudio ${file.name}")

        isAudioPlaying = true
        mediaPlayer = MediaPlayer().apply {
            setOnCompletionListener {
                stopAudio()
            }
            setDataSource(file.absolutePath)
            prepare()
            start()
        }
    }

    fun stopAudio() {
        mediaPlayer.stop()
        isAudioPlaying = false
    }
}