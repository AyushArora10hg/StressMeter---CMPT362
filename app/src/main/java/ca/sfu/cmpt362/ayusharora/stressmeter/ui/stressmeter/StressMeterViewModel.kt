package ca.sfu.cmpt362.ayusharora.stressmeter.ui.stressmeter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StressMeterViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is StressMeter GridView Fragment"
    }
    val text: LiveData<String> = _text
}