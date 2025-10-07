package ca.sfu.cmpt362.ayusharora.stressmeter.imagerequest

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ca.sfu.cmpt362.ayusharora.stressmeter.R
import ca.sfu.cmpt362.ayusharora.stressmeter.databinding.FragmentImageRequestBinding
import ca.sfu.cmpt362.ayusharora.stressmeter.imageresponse.ImageResponse

class ImageRequestFragment : Fragment() {

    private var _binding: FragmentImageRequestBinding? = null
    private val binding get() = _binding!!
    private lateinit var imageAdapter: ImageAdapter
    private lateinit var imagesToShow: IntArray

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentImageRequestBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupGridView()
        handleButtonClick()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun setupGridView(){

        val gridView: GridView = binding.imageRequestGridview
        val imageResourceArray = resources.obtainTypedArray(R.array.images_1)
        imagesToShow = IntArray(imageResourceArray.length()) { i -> imageResourceArray.getResourceId(i, 0) }
        imageAdapter = ImageAdapter(requireContext(), imagesToShow)
        gridView.adapter = imageAdapter
        gridView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(requireContext(), ImageResponse::class.java)
            intent.putExtra("selectedImage", imagesToShow[position])
            startActivity(intent)
        }
        imageResourceArray.recycle()
    }
    private fun handleButtonClick () {

        val button = binding.imageRequestButtonMoreImages
        var displayArrayNumber = 1
        var imageResourceArray: TypedArray? = null
        button.setOnClickListener {
            when (displayArrayNumber){
                1->{
                    imageResourceArray = resources.obtainTypedArray(R.array.images_2)
                    displayArrayNumber = 2
                }
                2->{
                    imageResourceArray = resources.obtainTypedArray(R.array.images_3)
                    displayArrayNumber = 3
                }
                3->{
                    imageResourceArray = resources.obtainTypedArray(R.array.images_1)
                    displayArrayNumber = 1
                }
            }

            imagesToShow = IntArray(imageResourceArray?.length() ?: 0 ) { i -> imageResourceArray?.getResourceId(i, 0)
                ?: 0}
            imageResourceArray?.recycle()
            imageAdapter.updateImages(imagesToShow)
        }
    }
}