package ca.sfu.cmpt362.ayusharora.stressmeter.visualization

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ca.sfu.cmpt362.ayusharora.stressmeter.R
import ca.sfu.cmpt362.ayusharora.stressmeter.databinding.FragmentVisualizationBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class VisualizationFragment : Fragment() {

    private var _binding: FragmentVisualizationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentVisualizationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        CoroutineScope(Dispatchers.IO).launch{
            readFromCSV()
        }
        return root
    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }
    private suspend fun readFromCSV(){

        val file = File(requireContext().filesDir, "stress_level_data.csv")
        if (file.exists()){

            val lines = file.readLines()
            for (line in lines){
                val parts = line.split(",")
                val timestamp = parts[0]
                val stressLevel = parts[1]
                withContext(Dispatchers.Main) {
                    addToTable(timestamp, stressLevel)
                }
            }
        }
    }

    private fun addToTable(timestamp: String, stressLevel: String){

        val summaryTable: TableLayout = binding.visualizationTablelayout
        val newRow = TableRow(requireContext())

        val time = TextView(requireContext())
        time.text = timestamp
        time.gravity = Gravity.CENTER
        time.background = ContextCompat.getDrawable(requireContext(), R.drawable.cell_border)

        val stress = TextView(requireContext())
        stress.text = stressLevel
        stress.gravity = Gravity.CENTER
        stress.background = ContextCompat.getDrawable(requireContext(), R.drawable.cell_border)

        newRow.addView(time)
        newRow.addView(stress)
        summaryTable.addView(newRow)

        time.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        stress.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)

    }
}