package com.dara.ulessontask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dara.ulessontask.data.Chapter
import com.dara.ulessontask.databinding.ListItemChapterBinding

class ChapterAdapter(private val chapters: List<Chapter>, val context: Context) :
    RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder>() {

    private lateinit var chapterBinding: ListItemChapterBinding

    inner class ChapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = chapterBinding.chapterName
        private val recyclerView = chapterBinding.rvLessons

        fun bind(chapter: Chapter) {
            textView.text = chapter.name
            recyclerView.adapter = LessonAdapter(chapter.lessons, context)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        chapterBinding = ListItemChapterBinding.inflate(inflater, parent, false)
        return ChapterViewHolder(chapterBinding.root)
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val chapter = chapters[position]
        holder.bind(chapter)
    }

    override fun getItemCount(): Int {
        return chapters.size
    }
}

