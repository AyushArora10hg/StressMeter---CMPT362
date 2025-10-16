package ca.sfu.cmpt362.ayusharora.stressmeter.visualization

import androidx.lifecycle.ViewModel
import com.anychart.chart.common.dataentry.DataEntry

class VisualizationViewModel : ViewModel() {

    var dataLoaded = false
    val chartData = mutableListOf<DataEntry>()
    val tableRows = mutableListOf<Pair<String, String>>()
}