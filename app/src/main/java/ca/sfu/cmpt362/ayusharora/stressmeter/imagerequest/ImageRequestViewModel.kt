package ca.sfu.cmpt362.ayusharora.stressmeter.imagerequest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.sfu.cmpt362.ayusharora.stressmeter.R

// To preserve the state of my UI elements
// Current Images: The array of images currently being displayed
// Music Player: An instance of MusicPlayer to play music in continuity
class ImageRequestViewModel : ViewModel() {


    val currentImages = MutableLiveData<IntArray>()
    var musicPlayer: MyMusicPlayer? = null

}
