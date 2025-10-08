package ca.sfu.cmpt362.ayusharora.stressmeter.imageresponse

import android.content.pm.ActivityInfo
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
    private lateinit var binding: ActivityImageResponseBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivityImageResponseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayImage()
        handleButtonClicks()
    }

    private fun displayImage(){

        val imageView: ImageView = binding.imageResponseImageview
        val imageToShow = intent.getIntExtra("selectedImage", -1)
        if (imageToShow != -1) {
            imageView.setImageResource(imageToShow)
        }
    }
    private fun handleButtonClicks(){

        val submitButton: Button = binding.imageResponseButtonSubmit
        submitButton.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                writeToCSV()
            }
            finishAffinity()
        }

        val cancelButton : Button = binding.imageResponseButtonCancel
        cancelButton.setOnClickListener {
            finish()
        }
    }
    private fun writeToCSV(){

        val file = File(this.filesDir, "stress_level_data.csv")
        val timestamp = System.currentTimeMillis()
        val stressLevel = intent.getIntExtra("selectedImageID",-1)
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