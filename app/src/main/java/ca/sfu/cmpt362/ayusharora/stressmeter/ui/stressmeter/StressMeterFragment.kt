package ca.sfu.cmpt362.ayusharora.stressmeter.ui.stressmeter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ca.sfu.cmpt362.ayusharora.stressmeter.R
import ca.sfu.cmpt362.ayusharora.stressmeter.databinding.FragmentStressMeterBinding

class StressMeterFragment : Fragment() {

    private var _binding: FragmentStressMeterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val stressMeterViewModel =
            ViewModelProvider(this).get(StressMeterViewModel::class.java)

        _binding = FragmentStressMeterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.stressmeterTextview
        stressMeterViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val gridView : GridView = binding.stressmeterGridview
        val typedArray = resources.obtainTypedArray(R.array.images)
        val images = IntArray(typedArray.length()) { i -> typedArray.getResourceId(i, 0) }
        typedArray.recycle()
        val imageAdapter = StressMeterImageAdapter(requireContext(), images)
        gridView.adapter = imageAdapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}