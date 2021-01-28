package com.dara.ulessontask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dara.ulessontask.R
import com.dara.ulessontask.data.Lesson
import com.dara.ulessontask.databinding.ListItemLessonBinding

class LessonAdapter(private var lessons: List<Lesson>, private val context: Context) :
    RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    private lateinit var lessonBinding: ListItemLessonBinding

    inner class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = lessonBinding.lessonName
        private val imageView = lessonBinding.lessonIcon

        fun bind(lesson: Lesson) {
            textView.text = lesson.name
            Glide.with(context).load(lesson.icon).placeholder(R.drawable.subject_placeholder)
                .into(imageView)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonAdapter.LessonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        lessonBinding = ListItemLessonBinding.inflate(inflater, parent, false)
        return LessonViewHolder(lessonBinding.root)
    }

    override fun onBindViewHolder(holder: LessonAdapter.LessonViewHolder, position: Int) {
        val lesson = lessons[position]
        holder.bind(lesson)
    }

    override fun getItemCount(): Int {
        return lessons.size
    }
}

