package id.my.mirzaa.lira

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.my.mirzaa.lira.databinding.ActivityMainBinding
import id.my.mirzaa.lira.databinding.ActivityPatientBaseBinding

class PatientBase : AppCompatActivity() {
    private val binding by lazy { ActivityPatientBaseBinding.inflate(layoutInflater) }
    private val patientHomeFragment = PatientHomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerPatient, patientHomeFragment)
            .commit()

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