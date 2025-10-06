package ca.sfu.cmpt362.ayusharora.stressmeter.imageresponse

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ca.sfu.cmpt362.ayusharora.stressmeter.databinding.ActivityImageResponseBinding

class ImageResponse: AppCompatActivity() {

    private lateinit var binding: ActivityImageResponseBinding
    private lateinit var imageView: ImageView
    private lateinit var submitButton: Button
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivityImageResponseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageView = binding.imageResponseImageview

        val imageId = intent.getIntExtra("selectedImage", 0)

        if (imageId != 0) {
            imageView.setImageResource(imageId)
        }

        submitButton = binding.imageResponseButtonSubmit
        submitButton.setOnClickListener{
            Toast.makeText(this, "Response recorded!", Toast.LENGTH_SHORT).show()
            finishAffinity()
        }

        cancelButton = binding.imageResponseButtonCancel
        cancelButton.setOnClickListener {
            finish()
        }
    }
}