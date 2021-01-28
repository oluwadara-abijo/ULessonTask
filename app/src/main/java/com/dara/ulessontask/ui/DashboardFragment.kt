package com.dara.ulessontask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dara.ulessontask.R
import com.dara.ulessontask.SubjectAdapter
import com.dara.ulessontask.adapters.RecentLessonAdapter
import com.dara.ulessontask.data.ApiResponse
import com.dara.ulessontask.data.RecentLesson
import com.dara.ulessontask.data.Resource
import com.dara.ulessontask.data.Subject
import com.dara.ulessontask.databinding.FragmentDashboardBinding
import com.dara.ulessontask.viewmodel.MainViewModel

class DashboardFragment : Fragment(R.layout.fragment_dashboard), SubjectAdapter.ItemClickListener,
    RecentLessonAdapter.ItemClickListener {

    private val viewModel by viewModels<MainViewModel>()
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var subjectAdapter: SubjectAdapter
    private lateinit var subjects: List<Subject>
    private lateinit var recentLessonAdapter: RecentLessonAdapter
    private lateinit var recentLessons: List<RecentLesson>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subjects = listOf()
        displaySubjects()
        getContent()
    }

    /**
     * Get subjects from database or server if database is empty
     */
    private fun getContent() {
        //Get subjects from database
        viewModel.contentFromDatabase?.observe(viewLifecycleOwner, { list ->
            subjects = list
            subjectAdapter.setSubjects(subjects)
            displayRecentLessons()
            if (subjects.isEmpty()) {
                // The database has no subject records; get data from the server
                viewModel.contentFromServer.observe(viewLifecycleOwner, {
                    when (it) {
                        is Resource.Loading -> Toast.makeText(
                            requireContext(),
                            "Loading...",
                            Toast.LENGTH_SHORT
                        ).show()
                        is Resource.Success -> {
                            subjectAdapter.setSubjects((it.data as ApiResponse).data.subjects)
                        }
                        is Resource.Failure -> Toast.makeText(
                            requireContext(),
                            "Error...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            }
        })

    }

    private fun displaySubjects() {
        subjectAdapter = SubjectAdapter(subjects, requireContext(), this)
        val gridLayoutManager =
            GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        binding.rvSubjects.apply {
            layoutManager = gridLayoutManager
            adapter = subjectAdapter
        }
    }

    private fun displayRecentLessons() {
        viewModel.recentLessons?.observe(viewLifecycleOwner, {
            recentLessons = it
            if (it.isNotEmpty()) {
                binding.groupRecent.visibility = View.VISIBLE
                recentLessonAdapter = RecentLessonAdapter(recentLessons, this)
                val linearLayoutManager = LinearLayoutManager(requireContext())
                binding.rvRecentLessons.apply {
                    layoutManager = linearLayoutManager
                    adapter = recentLessonAdapter
                }
            }

        })
    }

    override fun onItemClick(subject: Subject) {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(
            R.id.fragment_container, ChaptersFragment.newInstance(subject)
        )?.addToBackStack(null)?.commit()
    }

    override fun onItemClick(lesson: RecentLesson) {
        TODO("Not yet implemented")
    }
}