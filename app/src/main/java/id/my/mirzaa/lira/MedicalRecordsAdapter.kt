package id.my.mirzaa.lira

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.my.mirzaa.lira.databinding.MedicalRecordItemBinding
import java.io.Serializable

data class MedicalRecord(
    val title: String,
    val description: String,
    val doctor: String,
    val systolic: String,
    val diastolic: String,
    val cholesterol: String,
    val bloodSugar: String,
    val chestPain: String,
    val ecg: String,
    val oldpeak: String,
) : Serializable

class MedicalRecordAdapter(private val records: List<MedicalRecord>) :
    RecyclerView.Adapter<MedicalRecordAdapter.ViewHolder>() {
    class ViewHolder(private val binding: MedicalRecordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(record: MedicalRecord) {
            binding.txtTitle.text = record.title
            binding.txtDescription.text = record.description
            binding.root.setOnClickListener {
                Intent(binding.root.context, MedicalRecordActivity::class.java).also {
                    it.putExtra(MEDICAL_RECORD_ARG, record)
                    binding.root.context.startActivity(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MedicalRecordItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(records[position])

    override fun getItemCount() = records.size
}