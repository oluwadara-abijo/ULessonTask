package com.dara.ulessontask

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dara.ulessontask.data.Subject
import com.dara.ulessontask.databinding.ListItemSubjectBinding

class SubjectAdapter(private var subjects: List<Subject>, private val context: Context) :
    RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {

    private lateinit var binding: ListItemSubjectBinding

    inner class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView = binding.imgSubjectIcon

        fun bind(subject: Subject) {

            // Replacing the icons from the API with the ones from Figma because 😞
            val icon = when (subject.name) {
                "Mathematics" -> R.drawable.ic_mth
                "English" -> R.drawable.ic_eng
                "Chemistry" -> R.drawable.ic_chm
                "Biology" -> R.drawable.ic_bio
                "Physics" -> R.drawable.ic_phy
                else -> return
            }

            // TODO Change icon to image from API
            Glide.with(context).load(icon).placeholder(R.drawable.subject_placeholder)
                .into(imageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ListItemSubjectBinding.inflate(inflater, parent, false)
        return SubjectViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val currentSubject = subjects[position]
        holder.bind(currentSubject)
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    internal fun setSubjects(subjects: List<Subject>) {
        this.subjects = subjects
        notifyDataSetChanged()
    }
}