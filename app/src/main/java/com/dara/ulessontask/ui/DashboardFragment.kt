package com.dara.ulessontask.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dara.ulessontask.R
import com.dara.ulessontask.data.ApiResponse
import com.dara.ulessontask.data.Resource
import com.dara.ulessontask.viewmodel.MainViewModel

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getContent()
    }

    private fun getContent() {
        viewModel.content.observe(viewLifecycleOwner, {
            println("Res - $it")
            when (it) {
                is Resource.Loading -> Toast.makeText(
                    requireContext(),
                    "Loading...",
                    Toast.LENGTH_SHORT
                ).show()
                is Resource.Success -> {
                    var string = ""
                    val content = it.data as ApiResponse
                    for (subject in content.data.subjects) {
                        string += subject.name + "\n"
                    }
                    Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
                }
                is Resource.Failure -> Toast.makeText(
                    requireContext(),
                    "Error...",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }
}