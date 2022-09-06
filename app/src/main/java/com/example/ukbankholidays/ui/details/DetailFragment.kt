package com.example.ukbankholidays.ui.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ukbankholidays.R
import com.example.ukbankholidays.databinding.DetailFragmentBinding
import com.example.ukbankholidays.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {

    private lateinit var binding: DetailFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel by activityViewModels<MainViewModel>()

        binding = DetailFragmentBinding.bind(view)

        val recyclerDetails = binding.detailsRecycler
        recyclerDetails.apply {
            background = AppCompatResources.getDrawable(requireContext(), R.color.white)
            layoutManager = LinearLayoutManager(context)
            adapter = DetailsAdapter(
                when (viewmodel.selected) {
                    0 -> viewmodel.responseSaved.englandAndWales.events.sortedByDescending { it.date }
                    1 -> viewmodel.responseSaved.scotland.events.sortedByDescending { it.date }
                    else -> viewmodel.responseSaved.northernIreland.events.sortedByDescending { it.date }
                },
                context
            )
        }

    }
}