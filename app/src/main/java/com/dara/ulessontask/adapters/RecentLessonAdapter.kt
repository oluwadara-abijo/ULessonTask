package com.dara.ulessontask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dara.ulessontask.data.RecentLesson
import com.dara.ulessontask.databinding.ListItemRecentLessonBinding

class RecentLessonAdapter(
    private var lessons: List<RecentLesson>,
    private val listener: ItemClickListener
) :
    RecyclerView.Adapter<RecentLessonAdapter.LessonViewHolder>() {

    private lateinit var lessonBinding: ListItemRecentLessonBinding

    // Handle item clicks
    interface ItemClickListener {
        fun onItemClick(lesson: RecentLesson)
    }

    inner class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewSubject = lessonBinding.tvLessonName
        private val textViewLesson = lessonBinding.tvLessonSubject

        fun bind(lesson: RecentLesson) {
            textViewSubject.text = lesson.subjectName
            textViewLesson.text = lesson.name
            itemView.setOnClickListener { listener.onItemClick(lesson) }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecentLessonAdapter.LessonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        lessonBinding = ListItemRecentLessonBinding.inflate(inflater, parent, false)
        return LessonViewHolder(lessonBinding.root)
    }

    override fun onBindViewHolder(holder: RecentLessonAdapter.LessonViewHolder, position: Int) {
        val lesson = lessons[position]
        holder.bind(lesson)
    }

    override fun getItemCount(): Int {
        return lessons.size
    }
}

