package ca.sfu.cmpt362.ayusharora.stressmeter.imagerequest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ca.sfu.cmpt362.ayusharora.stressmeter.R
import ca.sfu.cmpt362.ayusharora.stressmeter.databinding.FragmentImageRequestBinding
import ca.sfu.cmpt362.ayusharora.stressmeter.imageresponse.ImageResponse

class ImageRequestFragment : Fragment() {

    private val images1 = intArrayOf(
        R.drawable.set1_0, R.drawable.set1_1, R.drawable.set1_2, R.drawable.set1_3,
        R.drawable.set1_4, R.drawable.set1_5, R.drawable.set1_6, R.drawable.set1_7,
        R.drawable.set1_8, R.drawable.set1_9, R.drawable.set1_10, R.drawable.set1_11,
        R.drawable.set1_12, R.drawable.set1_13, R.drawable.set1_14, R.drawable.set1_15
    )
    private val images2 = intArrayOf(
        R.drawable.set2_0, R.drawable.set2_1, R.drawable.set2_2, R.drawable.set2_3,
        R.drawable.set2_4, R.drawable.set2_5, R.drawable.set2_6, R.drawable.set2_7,
        R.drawable.set2_8, R.drawable.set2_9, R.drawable.set2_10, R.drawable.set2_11,
        R.drawable.set2_12, R.drawable.set2_13, R.drawable.set2_14, R.drawable.set2_15
    )
    private val images3 = intArrayOf(
        R.drawable.set3_0, R.drawable.set3_1, R.drawable.set3_2, R.drawable.set3_3,
        R.drawable.set3_4, R.drawable.set3_5, R.drawable.set3_6, R.drawable.set3_7,
        R.drawable.set3_8, R.drawable.set3_9, R.drawable.set3_10, R.drawable.set3_11,
        R.drawable.set3_12, R.drawable.set3_13, R.drawable.set3_14, R.drawable.set3_15
    )
    private var _binding: FragmentImageRequestBinding? = null
    private val binding get() = _binding!!
    private lateinit var imageRequestViewModel: ImageRequestViewModel
    private lateinit var imageAdapter: ImageAdapter
    private lateinit var imagesToShow: IntArray

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentImageRequestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        imageRequestViewModel = ViewModelProvider(requireActivity())[ImageRequestViewModel::class.java]

        imageRequestViewModel.currentImages.observe(viewLifecycleOwner) { images ->
            imagesToShow = images
            imageAdapter.updateImages(images)
        }

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
        imagesToShow = images1
        imageAdapter = ImageAdapter(requireContext(), imagesToShow)
        gridView.adapter = imageAdapter
        gridView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(requireActivity(), ImageResponse::class.java)
            intent.putExtra("selectedImage", imagesToShow[position])
            intent.putExtra("selectedImageID",position)
            startActivity(intent)
        }
    }
    private fun handleButtonClick () {

        val button = binding.imageRequestButtonMoreImages
        var displayArrayNumber = 1
        button.setOnClickListener {
            when (displayArrayNumber){
                1->{
                    imagesToShow =  images2
                    displayArrayNumber = 2
                }
                2->{
                    imagesToShow =  images3
                    displayArrayNumber = 3
                }
                3->{
                    imagesToShow =  images1
                    displayArrayNumber = 1
                }
            }
            imageRequestViewModel.updateImages(imagesToShow)
        }
    }
}