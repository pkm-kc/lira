package id.my.mirzaa.lira

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.my.mirzaa.lira.databinding.FragmentPatientDailyReportBinding


class PatientDailyReportFragment : Fragment(R.layout.fragment_patient_daily_report) {
    private var _binding: FragmentPatientDailyReportBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPatientDailyReportBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}