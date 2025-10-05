package ca.sfu.cmpt362.ayusharora.stressmeter.visualization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VisualizationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Visualization Fragment"
    }
    val text: LiveData<String> = _text
}