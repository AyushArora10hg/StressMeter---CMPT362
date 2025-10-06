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
    private lateinit var displayArray : TypedArray
    private  var displayArrayNumber : Int = 1
    private lateinit var gridView : GridView
    private lateinit var imageAdapter: ImageAdapter
    private lateinit var images: IntArray

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val imageRequestViewModel =
            ViewModelProvider(this).get(ImageRequestViewModel::class.java)

        _binding = FragmentImageRequestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.imageRequestTextview
        imageRequestViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        gridView = binding.imageRequestGridview
        displayArray = resources.obtainTypedArray(R.array.images_1)
        images = IntArray(displayArray.length()) { i -> displayArray.getResourceId(i, 0) }
        displayArray.recycle()
        imageAdapter = ImageAdapter(requireContext(), images)
        gridView.adapter = imageAdapter

        gridView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(requireContext(), ImageResponse::class.java)
            intent.putExtra("selectedImage", images[position])
            startActivity(intent)
        }

        val button = binding.imageRequestButtonMoreImages
            button.setOnClickListener {
                when (displayArrayNumber){
                    1->{
                        displayArray = resources.obtainTypedArray(R.array.images_2)
                        displayArrayNumber = 2
                    }
                    2->{
                        displayArray = resources.obtainTypedArray(R.array.images_3)
                        displayArrayNumber = 3
                    }
                    3->{
                        displayArray = resources.obtainTypedArray(R.array.images_1)
                        displayArrayNumber = 1
                    }
                }

                images = IntArray(displayArray.length()) { i -> displayArray.getResourceId(i, 0) }
                displayArray.recycle()
                imageAdapter.updateImages(images)
            }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}