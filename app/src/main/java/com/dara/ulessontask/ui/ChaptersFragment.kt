package com.dara.ulessontask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dara.ulessontask.R
import com.dara.ulessontask.adapters.ChapterAdapter
import com.dara.ulessontask.adapters.LessonAdapter
import com.dara.ulessontask.data.Chapter
import com.dara.ulessontask.data.Lesson
import com.dara.ulessontask.data.Subject
import com.dara.ulessontask.databinding.FragmentChaptersBinding

class ChaptersFragment : Fragment(R.layout.fragment_chapters), LessonAdapter.ItemClickListener {

    private var _binding: FragmentChaptersBinding? = null
    private val binding get() = _binding!!
    private lateinit var chapterAdapter: ChapterAdapter
    private lateinit var subject: Subject
    private lateinit var chapters: List<Chapter>

    companion object {
        private const val SUBJECT = "subject"

        /**
         * This factory method creates a new instance of this fragment using the provided parameters.
         * @param subject
         * @return A new instance of fragment ChaptersFragment.
         */
        @JvmStatic
        fun newInstance(subject: Subject) =
            ChaptersFragment().apply {
                arguments = Bundle().apply { putParcelable(SUBJECT, subject) }
            }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChaptersBinding.inflate(inflater, container, false)
        subject = arguments?.getParcelable(SUBJECT)!!
        chapters = subject.chapters
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set toolbar title
        binding.toolbar.title = subject.name
        displayChapters()

    }

    private fun displayChapters() {
        chapterAdapter = ChapterAdapter(chapters, requireContext(), this)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.rvChapters.apply {
            layoutManager = linearLayoutManager
            adapter = chapterAdapter
        }
    }

    override fun onItemClick(lesson: Lesson) {
        // When a lesson is clicked, navigate to PlayerFragment
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(
            R.id.fragment_container, PlayerFragment.newInstance(lesson)
        )?.addToBackStack(null)?.commit()
    }
}