package ca.sfu.cmpt362.ayusharora.stressmeter.imagerequest

import android.media.MediaPlayer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageRequestViewModel : ViewModel() {
    val currentImages = MutableLiveData<IntArray>()
}
