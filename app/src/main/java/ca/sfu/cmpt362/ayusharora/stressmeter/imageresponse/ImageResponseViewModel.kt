package ca.sfu.cmpt362.ayusharora.stressmeter.imageresponse

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ImageResponseViewModel : ViewModel(){

    // Image being displayed in the ImageView
    val selectedImage = MutableLiveData<Int>()

    // Use basic Java I/O operations to write to a CSV file.
    // The writer writes a string formatted as (timestamp, stress level) to a new line in the file
    // Timestamp: taken from System.currentTimeMillis()
    // Stress Level: Extracted from the selected file name provided by the previous activity via intent
    // (all my resource images are named as set#_$number, where $number is the stress level associated with that image)
    fun writeToCSV(context: Context, resourceFileName: String) {
        val file = File(context.filesDir, "stress_level_data.csv")

        // Initially used System.currentTimeMillis(), but took help from chatGPT to format it properly
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val stressLevel = resourceFileName.split("_")[1]
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val writer = FileWriter(file, true)
                writer.write("$timestamp, $stressLevel\n")
                writer.flush()
                writer.close()

            } catch (_: IOException) {
                println("File not found")
            }
        }
    }
}