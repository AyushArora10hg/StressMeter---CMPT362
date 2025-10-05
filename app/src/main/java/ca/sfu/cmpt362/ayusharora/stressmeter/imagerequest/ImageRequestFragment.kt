package ca.sfu.cmpt362.ayusharora.stressmeter.imagerequest

import android.content.Intent
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

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val imageRequest =
            ViewModelProvider(this).get(ImageRequest::class.java)

        _binding = FragmentImageRequestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.imageRequestTextview
        imageRequest.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val gridView : GridView = binding.imageRequestGridview
        val typedArray = resources.obtainTypedArray(R.array.images)
        val images = IntArray(typedArray.length()) { i -> typedArray.getResourceId(i, 0) }
        typedArray.recycle()
        val imageAdapter = ImageAdapter(requireContext(), images)
        gridView.adapter = imageAdapter

        gridView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(requireContext(), ImageResponse::class.java)
            intent.putExtra("selectedImage", images[position])
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}