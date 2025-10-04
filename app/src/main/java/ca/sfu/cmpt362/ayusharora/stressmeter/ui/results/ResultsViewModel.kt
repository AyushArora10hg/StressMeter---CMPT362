package ca.sfu.cmpt362.ayusharora.stressmeter.ui.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Results Fragment"
    }
    val text: LiveData<String> = _text
}