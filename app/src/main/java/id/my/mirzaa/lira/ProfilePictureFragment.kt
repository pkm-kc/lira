package id.my.mirzaa.lira

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.progressindicator.LinearProgressIndicator
import id.my.mirzaa.lira.databinding.FragmentProfilePictureBinding

class ProfilePictureFragment : Fragment() {
    private var _binding: FragmentProfilePictureBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilePictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as ProfileSetupActivity).findViewById<LinearProgressIndicator>(R.id.stepper).progress =
            200

        binding.btnNext.setOnClickListener {
            Intent(requireContext(), PatientBaseActivity::class.java).also { startActivity(it) }
        }
    }
}