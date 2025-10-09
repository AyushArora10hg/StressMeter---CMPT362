package ca.sfu.cmpt362.ayusharora.stressmeter.imagerequest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageRequestViewModel : ViewModel() {
    val currentImages = MutableLiveData<IntArray>()
    var musicPlayer: MyMusicPlayer? = null
}
