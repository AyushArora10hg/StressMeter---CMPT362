package ca.sfu.cmpt362.ayusharora.stressmeter.imageresponse

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ca.sfu.cmpt362.ayusharora.stressmeter.databinding.ActivityImageResponseBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ImageResponse: AppCompatActivity() {

    // Binding given by default android studio code
    private lateinit var binding: ActivityImageResponseBinding

    private lateinit var imageResponseViewModel: ImageResponseViewModel

    // Added my UI elements in the default method given by android studio
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityImageResponseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageResponseViewModel = ViewModelProvider(this)[ImageResponseViewModel::class.java]
        val selectedImage = intent.getIntExtra("selectedImage", -1)
        if (selectedImage != -1){
            imageResponseViewModel.selectedImage.value = selectedImage
        }
        imageResponseViewModel.selectedImage.observe(this){selectedImage ->
            binding.imageResponseImageview.setImageResource(selectedImage)
        }

        handleButtonClicks()
    }

    // Button handler method
    // Submit Button: writes the data (stress level and timestamp) to CSV and closes the app
    // Cancel Button: returns to the previous activity (Image Response)
    private fun handleButtonClicks(){

        val submitButton: Button = binding.imageResponseButtonSubmit
        submitButton.setOnClickListener{
            val resourceFileName = resources.getResourceEntryName(intent.getIntExtra("selectedImage", -1))
            imageResponseViewModel.writeToCSV(this, resourceFileName)
            //TODO: Change to finishAffinity()
            finish()
        }

        val cancelButton : Button = binding.imageResponseButtonCancel
        cancelButton.setOnClickListener {
            finish()
        }
    }
}