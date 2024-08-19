package id.my.mirzaa.lira

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.my.mirzaa.lira.databinding.ActivityProfileSetupBinding

class ProfileSetupActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileSetupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileSetupBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_profile_setup)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fcContent, PersonalDataFragment())
            .commit()
        binding.topAppBar.setNavigationOnClickListener { finish() }
    }
}