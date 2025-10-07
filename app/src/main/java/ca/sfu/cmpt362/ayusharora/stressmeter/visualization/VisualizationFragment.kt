package ca.sfu.cmpt362.ayusharora.stressmeter.visualization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ca.sfu.cmpt362.ayusharora.stressmeter.databinding.FragmentVisualizationBinding

class VisualizationFragment : Fragment() {

    private var _binding: FragmentVisualizationBinding? = null
    private val binding get() = _binding!!
    private lateinit var summaryTable: TableLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentVisualizationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        summaryTable = binding.visualizationTablelayout
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}