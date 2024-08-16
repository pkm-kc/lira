package id.my.mirzaa.lira

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import id.my.mirzaa.lira.databinding.FragmentDailyCheckupUnavailableBinding

class DailyCheckupUnavailableFragment : Fragment() {
    private var _binding: FragmentDailyCheckupUnavailableBinding? = null
    private val binding get() = _binding!!
    private lateinit var healthConnectManager: HealthConnectManager
    private lateinit var checkPermission: ActivityResultLauncher<Set<String>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyCheckupUnavailableBinding.inflate(inflater, container, false)
        healthConnectManager =
            (requireActivity().application as BaseApplication).healthConnectManager
        checkPermission =
            registerForActivityResult(healthConnectManager.requestPermissionsActivityContract()) { granted ->
                if (granted.containsAll(PERMISSIONS)) {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fcDailyCheckup, DailyCheckupContentFragment())
                        .commit()
                } else {
                    Log.i("HealthConnect", "Not all permissions granted")
                    Log.d("HealthConnect", granted.toString())
                }
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnConnect.setOnClickListener {
            if (healthConnectManager.availability.value != HealthConnectAvailability.INSTALLED) {
                return@setOnClickListener
            }
            checkPermission.launch(PERMISSIONS)
        }
    }
}