package ca.sfu.cmpt362.ayusharora.stressmeter.imagerequest

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// To preserve the state of my UI elements
// Current Images: The array of images currently being displayed
// Music Player: An instance of MusicPlayer to play music in continuity
class ImageRequestViewModel : ViewModel() {

    val currentImages = MutableLiveData<IntArray>()
    var musicPlayer: MyMusicPlayer? = null
    private var vibrator: Vibrator? = null
    private var isVibrating = false

    @RequiresApi(Build.VERSION_CODES.O)
    fun startVibration(context: Context) {
        if (vibrator == null) {
            vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }

        if (!isVibrating) {
            val pattern = longArrayOf(0, 500, 500)
            val effect = VibrationEffect.createWaveform(pattern, 0)
            vibrator?.vibrate(effect)
            isVibrating = true
        }
    }

    fun stopVibration() {
        vibrator?.cancel()
        isVibrating = false
    }

}
