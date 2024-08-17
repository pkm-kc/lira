package id.my.mirzaa.lira

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.BloodPressureRecord
import androidx.health.connect.client.records.HeartRateRecord
import androidx.health.connect.client.records.StepsCadenceRecord
import androidx.health.connect.client.records.StepsRecord
import com.google.android.material.snackbar.Snackbar
import id.my.mirzaa.lira.databinding.FragmentPatientHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


val PERMISSIONS = setOf(
    HealthPermission.getReadPermission(HeartRateRecord::class),
    HealthPermission.getReadPermission(BloodPressureRecord::class),
    HealthPermission.getReadPermission(StepsRecord::class),
    HealthPermission.getReadPermission(StepsCadenceRecord::class),
)

class PatientHomeFragment : Fragment() {
    private var _binding: FragmentPatientHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var healthConnectManager: HealthConnectManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPatientHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        healthConnectManager =
            (requireActivity().application as BaseApplication).healthConnectManager

        binding.btnDailyReport.setOnClickListener {
            Snackbar.make(
                binding.btnDailyReport,
                "Perangkat tidak mendukung smartwatch",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        CoroutineScope(Dispatchers.Main).launch {
            if (
                healthConnectManager.availability.value == HealthConnectAvailability.INSTALLED
                && healthConnectManager.hasAllPermissions(PERMISSIONS)
            ) {
                DailyCheckupContentFragment().let {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.fcDailyCheckup, it)
                        .commit()
                }
            }
        }

        binding.btnDailyReport.setOnClickListener {
            val intent = Intent(requireContext(), PatientDailyReportActivity::class.java)
            startActivity(intent)
        }

        binding.btnMedicalRecord.setOnClickListener {
            val intent = Intent(requireContext(), MedicalRecordsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}