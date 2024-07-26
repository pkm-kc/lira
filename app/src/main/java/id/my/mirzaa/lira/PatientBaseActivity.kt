package id.my.mirzaa.lira

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import id.my.mirzaa.lira.databinding.ActivityPatientBaseBinding

class PatientBaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPatientBaseBinding
    private val patientHomeFragment = PatientHomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityPatientBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.menuNavigation.setOnItemSelectedListener { item ->
            val selectedFragment = when (item.itemId) {
                R.id.itemHome, R.id.itemSOS, R.id.itemProfile -> patientHomeFragment
                else -> null
            }
            selectedFragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerPatient, it)
                    .commit()
                true
            } ?: false
        }
    }
}