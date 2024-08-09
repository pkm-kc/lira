package id.my.mirzaa.lira

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import id.my.mirzaa.lira.databinding.FragmentPatientHomeBinding
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.WeekFields

class PatientHomeFragment : Fragment(R.layout.fragment_patient_home) {
    private var _binding: FragmentPatientHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPatientHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val now = LocalDate.now()
        val fieldISO = WeekFields.of(DayOfWeek.MONDAY, 1).dayOfWeek()
        var start = now.with(fieldISO, 1)
        when (now.dayOfWeek.value) {
            1 -> setDayActive(binding.root.context, binding.llDayMonday, binding.tvDayMonday, binding.tvDateMonday)
            2 -> setDayActive(binding.root.context, binding.llDayTuesday, binding.tvDayTuesday, binding.tvDateTuesday)
            3 -> setDayActive(binding.root.context, binding.llDayWednesday, binding.tvDayWednesday, binding.tvDateWednesday)
            4 -> setDayActive(binding.root.context, binding.llDayThursday, binding.tvDayThursday, binding.tvDateThursday)
            5 -> setDayActive(binding.root.context, binding.llDayFriday, binding.tvDayFriday, binding.tvDateFriday)
            6 -> setDayActive(binding.root.context, binding.llDaySaturday, binding.tvDaySaturday, binding.tvDateSaturday)
            7 -> setDayActive(binding.root.context, binding.llDaySunday, binding.tvDaySunday, binding.tvDateSunday)
        }
        for (id in DATE_NUMBER_IDS) {
            binding.root.findViewById<TextView>(id).text = start.dayOfMonth.toString()
            start = start.plusDays(1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val DATE_NUMBER_IDS = intArrayOf(
            R.id.tvDateMonday,
            R.id.tvDateTuesday,
            R.id.tvDateWednesday,
            R.id.tvDateThursday,
            R.id.tvDateFriday,
            R.id.tvDateSaturday,
            R.id.tvDateSunday
        )

        private fun setDayActive(context: Context ,container: LinearLayout, day: TextView, date: TextView) {
            container.setBackgroundResource(R.drawable.current_active_day)
            day.setTextColor(ContextCompat.getColor(context, R.color.white))
            date.setTextColor(ContextCompat.getColor(context, R.color.white))
        }
    }
}