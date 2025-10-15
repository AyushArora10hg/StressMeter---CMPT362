package ca.sfu.cmpt362.ayusharora.stressmeter.imagerequest

import android.content.Context
import android.media.MediaPlayer

// Created my own Music Player class so that I can store the point of pause.
// Music is paused when the activity playing music is rotated or new activity is started.
// In each case I store the point of pause in a private variable and when the activity is resumed,
// the pause point is used to play the music from that very point.
class MyMusicPlayer(private val context: Context, private val resource: Int ) {

    // Pause point
    private var musicStartPoint = 0
    // MediaPlayer to play the music
    private var mediaPlayer: MediaPlayer? = null

    // Initialize and load music resource to the Media Player
    fun start () {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, resource)
            mediaPlayer?.isLooping = true
        }
        mediaPlayer?.start()
    }

    // Pause the Media Player and store the pause point
    fun pause() {
        musicStartPoint = mediaPlayer?.currentPosition ?: 0
        mediaPlayer?.pause()
    }

    // Resume the music player from the pause point
    fun resume(){
        mediaPlayer?.seekTo(musicStartPoint)
        mediaPlayer?.start()
    }
}