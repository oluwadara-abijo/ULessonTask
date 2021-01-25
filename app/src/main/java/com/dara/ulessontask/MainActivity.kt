package com.dara.ulessontask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.dara.ulessontask.data.ApiResponse
import com.dara.ulessontask.data.Resource
import com.dara.ulessontask.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.content.observe(this, {
            println("Res - $it")
            when (it) {
                is Resource.Loading -> Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
                is Resource.Success -> {
                    var string = ""
                    val content = it.data as ApiResponse
                    for (subject in content.data.subjects) {
                        string += subject.name + "\n"
                    }
                    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
                }
                is Resource.Failure -> Toast.makeText(this, "Error...", Toast.LENGTH_SHORT).show()
            }

        })
    }
}