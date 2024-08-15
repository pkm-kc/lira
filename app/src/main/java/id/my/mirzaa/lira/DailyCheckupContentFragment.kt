package id.my.mirzaa.lira

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import id.my.mirzaa.lira.databinding.FragmentDailyCheckupContentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.WeekFields


private val DATE_NUMBER_IDS = intArrayOf(
    R.id.tvDateMonday,
    R.id.tvDateTuesday,
    R.id.tvDateWednesday,
    R.id.tvDateThursday,
    R.id.tvDateFriday,
    R.id.tvDateSaturday,
    R.id.tvDateSunday
)

private fun setDayActive(
    context: Context, container: LinearLayout, day: TextView, date: TextView
) {
    container.setBackgroundResource(R.drawable.current_active_day)
    day.setTextColor(ContextCompat.getColor(context, R.color.white))
    date.setTextColor(ContextCompat.getColor(context, R.color.white))
}

class DailyCheckupContentFragment : Fragment() {
    private var _binding: FragmentDailyCheckupContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var healthConnectManager: HealthConnectManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyCheckupContentBinding.inflate(inflater, container, false)
        healthConnectManager =
            (requireActivity().application as BaseApplication).healthConnectManager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val now = LocalDate.now()
        val fieldISO = WeekFields.of(DayOfWeek.MONDAY, 1).dayOfWeek()
        var start = now.with(fieldISO, 1)
        when (now.dayOfWeek.value) {
            1 -> setDayActive(
                binding.root.context, binding.llDayMonday, binding.tvDayMonday, binding.tvDateMonday
            )

            2 -> setDayActive(
                binding.root.context,
                binding.llDayTuesday,
                binding.tvDayTuesday,
                binding.tvDateTuesday
            )

            3 -> setDayActive(
                binding.root.context,
                binding.llDayWednesday,
                binding.tvDayWednesday,
                binding.tvDateWednesday
            )

            4 -> setDayActive(
                binding.root.context,
                binding.llDayThursday,
                binding.tvDayThursday,
                binding.tvDateThursday
            )

            5 -> setDayActive(
                binding.root.context, binding.llDayFriday, binding.tvDayFriday, binding.tvDateFriday
            )

            6 -> setDayActive(
                binding.root.context,
                binding.llDaySaturday,
                binding.tvDaySaturday,
                binding.tvDateSaturday
            )

            7 -> setDayActive(
                binding.root.context, binding.llDaySunday, binding.tvDaySunday, binding.tvDateSunday
            )
        }
        for (id in DATE_NUMBER_IDS) {
            binding.root.findViewById<TextView>(id).text = start.dayOfMonth.toString()
            start = start.plusDays(1)
        }

        CoroutineScope(Dispatchers.Main).launch {
            val heartRates = healthConnectManager.readHearRates(Instant.EPOCH, Instant.now())
            val bloodPressures =
                healthConnectManager.readBloodPressures(Instant.EPOCH, Instant.now())
            val steps = healthConnectManager.readSteps(Instant.EPOCH, Instant.now())
            Log.d("HealthConnect", heartRates.toString())
            heartRates.maxByOrNull { it.endTime }?.samples?.firstOrNull()?.let {
                binding.txtHeartRateValue.text = "${it.beatsPerMinute.toString()} bpm"
            }
            bloodPressures.maxByOrNull { it.time }?.let {
                binding.txtBloodPressureValue.text =
                    "${it.systolic.toString()}/${it.diastolic.toString()}"
            }
            steps.maxByOrNull { it.endTime }?.let {
                binding.txtFootStepsValue.text = "${it.count.toString()} steps"
            }
        }
    }
}