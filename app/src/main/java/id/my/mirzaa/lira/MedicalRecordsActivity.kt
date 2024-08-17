package id.my.mirzaa.lira

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.my.mirzaa.lira.databinding.ActivityMedicalRecordsBinding

private val MEDICAL_RECORDS = mutableListOf(
    MedicalRecord(
        title = "Record 4 Juni 2024",
        description = "Kondisi pasien membaik",
        doctor = "Dr. Abdillah Lubis, M. Ked(Cardio), Sp. JP, FIHA",
        systolic = "120",
        diastolic = "80",
        cholesterol = "400",
        bloodSugar = "110",
        chestPain = "Atypical angina",
        ecg = "Normal",
        oldpeak = "4"
    ),
    MedicalRecord(
        title = "Record 11 Mei 2024",
        description = "Kondisi pasien memburuk pada beberapa...",
        doctor = "Dr. Abdillah Lubis, M. Ked(Cardio), Sp. JP, FIHA",
        systolic = "135",
        diastolic = "90",
        cholesterol = "420",
        bloodSugar = "130",
        chestPain = "Typical angina",
        ecg = "ST depression",
        oldpeak = "3.5"
    ),
    MedicalRecord(
        title = "Record 4 Mei 2024",
        description = "Kondisi pasien membaik dengan catatan...",
        doctor = "Dr. Abdillah Lubis, M. Ked(Cardio), Sp. JP, FIHA",
        systolic = "125",
        diastolic = "85",
        cholesterol = "380",
        bloodSugar = "105",
        chestPain = "Non-anginal pain",
        ecg = "Normal",
        oldpeak = "2"
    ),
    MedicalRecord(
        title = "Record 20 April 2024",
        description = "Pasien menjalani pemeriksaan rutin",
        doctor = "Dr. Abdillah Lubis, M. Ked(Cardio), Sp. JP, FIHA",
        systolic = "118",
        diastolic = "78",
        cholesterol = "390",
        bloodSugar = "108",
        chestPain = "Asymptomatic",
        ecg = "Normal",
        oldpeak = "1.5"
    ),
    MedicalRecord(
        title = "Record 1 April 2024",
        description = "Evaluasi awal kondisi pasien",
        doctor = "Dr. Abdillah Lubis, M. Ked(Cardio), Sp. JP, FIHA",
        systolic = "130",
        diastolic = "88",
        cholesterol = "410",
        bloodSugar = "120",
        chestPain = "Atypical angina",
        ecg = "ST elevation",
        oldpeak = "3"
    )
)

class MedicalRecordsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMedicalRecordsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicalRecordsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.setNavigationOnClickListener { finish() }

        val adapter = MedicalRecordAdapter(MEDICAL_RECORDS)
        binding.rvMedicalRecords.layoutManager = LinearLayoutManager(this)
        binding.rvMedicalRecords.adapter = adapter
    }
}