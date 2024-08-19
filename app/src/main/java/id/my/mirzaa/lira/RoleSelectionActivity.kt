package id.my.mirzaa.lira

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.my.mirzaa.lira.databinding.ActivityRoleSelectionBinding

class RoleSelectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoleSelectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoleSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.setNavigationOnClickListener { finish() }
        binding.clRolePatient.setOnClickListener {
            Intent(this, ProfileSetupActivity::class.java).also { startActivity(it) }
        }
    }
}