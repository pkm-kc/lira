package id.my.mirzaa.lira

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.my.mirzaa.lira.databinding.ActivityMedicalRecordBinding

const val MEDICAL_RECORD_ARG = "MEDICAL_RECORD_ARG"

class MedicalRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMedicalRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicalRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.setNavigationOnClickListener { finish() }
        val record = intent.getSerializableExtra(MEDICAL_RECORD_ARG) as MedicalRecord
        binding.txtDoctor.text = record.doctor
        binding.tiBloodPressure.editText?.setText("${record.systolic}/${record.diastolic} mmHg")
        binding.tiCholesterol.editText?.setText("${record.cholesterol} mg/dl")
        binding.tiBloodSugar.editText?.setText("${record.bloodSugar} mg/dl")
        binding
        binding.tiECG.editText?.setText(record.ecg)
        binding.tiOldpeak.editText?.setText(record.oldpeak)
    }
}