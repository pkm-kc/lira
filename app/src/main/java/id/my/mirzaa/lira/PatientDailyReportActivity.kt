package id.my.mirzaa.lira

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.health.connect.client.units.calories
import id.my.mirzaa.lira.databinding.ActivityPatientDailyReportBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant

class PatientDailyReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPatientDailyReportBinding
    private lateinit var healthConnectManager: HealthConnectManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientDailyReportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        healthConnectManager = (application as BaseApplication).healthConnectManager

        binding.topAppBar.setNavigationOnClickListener { finish() }

        CoroutineScope(Dispatchers.Main).launch {
            val heartRates = healthConnectManager.readHearRates(Instant.EPOCH, Instant.now())
            val bloodPressures =
                healthConnectManager.readBloodPressures(Instant.EPOCH, Instant.now())
            val steps = healthConnectManager.readSteps(Instant.EPOCH, Instant.now())
            val stepCadences = healthConnectManager.readStepCadences(Instant.EPOCH, Instant.now())
            heartRates.maxByOrNull { it.endTime }?.samples?.firstOrNull()?.let {
                binding.txtHeartRate.text = "${it.beatsPerMinute.toString()} bpm"
                binding.piHeartRate.progress = it.beatsPerMinute.toInt()
            }
            bloodPressures.maxByOrNull { it.time }?.let {
                binding.txtSystolic.text = it.systolic.toString()
                binding.txtDiastolic.text = it.diastolic.toString()
            }
            steps.maxByOrNull { it.endTime }?.let {
                binding.txtFootSteps.text = it.count.toString()
                binding.txtFootStepsCalorie.text = (it.count * 0.04).toInt().toString()
            }
            stepCadences.maxByOrNull { it.endTime }?.samples?.firstOrNull()?.let {
                binding.txtFootStepsTime.text = it.rate.toString()
            }
        }
    }
}