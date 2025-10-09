package ca.sfu.cmpt362.ayusharora.stressmeter.imagerequest

import android.content.Context
import android.media.MediaPlayer

class MyMusicPlayer(private val context: Context, private val resource: Int ) {

    private var musicStartPoint = 0
    private var mediaPlayer: MediaPlayer? = null

    fun start () {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, resource)
            mediaPlayer?.isLooping = true
        }
        mediaPlayer?.start()
    }

    fun pause() {
        musicStartPoint = mediaPlayer?.currentPosition ?: 0
        mediaPlayer?.pause()
    }

    fun resume(){
        mediaPlayer?.seekTo(musicStartPoint)
        mediaPlayer?.start()
    }
}