package com.example.ukbankholidays.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ukbankholidays.R
import com.example.ukbankholidays.databinding.MainFragmentBinding
import com.example.ukbankholidays.util.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {
    lateinit var binding: MainFragmentBinding
    val viewmodel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = MainFragmentBinding.bind(view)

        viewmodel.getData()

        CoroutineScope(Dispatchers.Main).launch {
            viewmodel.data.collect() { state ->
                when (state) {
                    is UIState.Loading -> {
                        Log.d("API Response: ", "LOADING")
                    }
                    is UIState.Fail -> {
                        Log.d("API Response: ", "Error -> ${state.error}")
                        Toast.makeText(
                            context,
                            "Unable to fetch the details, please try again later",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    is UIState.Success<*> -> {
                        initClickListeners()
                    }
                }
            }
        }
    }

    private fun initClickListeners() {
        binding.mcvEngland.setOnClickListener {
            viewmodel.selected = 0
            findNavController().navigate(R.id.action_mainFragment_to_detailsFragment)
        }
        binding.mcvScotland.setOnClickListener {
            viewmodel.selected = 1
            findNavController().navigate(R.id.action_mainFragment_to_detailsFragment)
        }
        binding.mcvNorthern.setOnClickListener {
            viewmodel.selected = 2
            findNavController().navigate(R.id.action_mainFragment_to_detailsFragment)
        }
    }
}