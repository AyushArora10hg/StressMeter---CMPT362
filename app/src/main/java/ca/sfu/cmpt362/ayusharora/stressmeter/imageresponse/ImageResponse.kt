package ca.sfu.cmpt362.ayusharora.stressmeter.imageresponse

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import ca.sfu.cmpt362.ayusharora.stressmeter.databinding.ActivityImageResponseBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileWriter
import java.io.IOException

class ImageResponse: AppCompatActivity() {

    // Binding given by default android studio code
    private lateinit var binding: ActivityImageResponseBinding

    // Added my UI elements in the default method given by android studio
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityImageResponseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayImage()
        handleButtonClicks()
    }

    // A helper method that displays the selected image (gets its name from intent.getIntExtra)
    private fun displayImage(){

        val imageView: ImageView = binding.imageResponseImageview
        val imageToShow = intent.getIntExtra("selectedImage", -1)
        if (imageToShow != -1) {
            imageView.setImageResource(imageToShow)
        }
    }

    // Button handler method
    // Submit Button: writes the data (stress level and timestamp) to CSV and closes the app
    // Cancel Button: returns to the previous activity (Image Response)
    private fun handleButtonClicks(){

        val submitButton: Button = binding.imageResponseButtonSubmit
        submitButton.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                writeToCSV()
            }
            //TODO: Change to finishAffinity()
            finish()
        }

        val cancelButton : Button = binding.imageResponseButtonCancel
        cancelButton.setOnClickListener {
            finish()
        }
    }

    // Use basic Java I/O operations to write to a CSV file.
    // The writer writes a string formatted as (timestamp, stress level) to a new line in the file
    // Timestamp: taken from System.currentTimeMillis()
    // Stress Level: Extracted from the selected file name provided by the previous activity via intent
    // (all my resource images are named as set#_$number, where $number is the stress level associated with that image)
    private fun writeToCSV(){

        val file = File(this.filesDir, "stress_level_data.csv")
        val timestamp = System.currentTimeMillis()
        val stressLevel = resources.getResourceEntryName(
            intent.getIntExtra("selectedImage", -1))
            .split("_")[1]
        try {
            val writer = FileWriter(file,true)
            writer.write("$timestamp, $stressLevel\n")
            writer.flush()
            writer.close()

        } catch (_: IOException){
            println("File not found")
        }
    }
}