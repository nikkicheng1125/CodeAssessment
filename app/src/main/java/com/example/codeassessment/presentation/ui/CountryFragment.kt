package com.example.codeassessment.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codeassessment.R
import com.example.codeassessment.databinding.FragmentCountryBinding
import com.example.codeassessment.domain.model.CountryState
import com.example.codeassessment.presentation.adapters.CountryAdapter
import com.example.codeassessment.presentation.viewmodel.CountryViewModel

class CountryFragment : Fragment() {
    private lateinit var binding: FragmentCountryBinding
    private lateinit var countryViewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View binding
        binding = FragmentCountryBinding.inflate(layoutInflater)
        // Get CountryViewModel
        countryViewModel = ViewModelProvider(requireActivity()).get(CountryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setUpObserver()
        return binding.root
    }

    private fun setUpObserver() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            countryViewModel.countryState.collect {
                when (it) {
                    // Action performed when returns Country Success state
                    is CountryState.Success -> {
                        binding.recyclerViewCountry.apply {
                            adapter = CountryAdapter(it.country)
                            layoutManager = LinearLayoutManager(requireContext())
                        }
                    }

                    // Action performed when returns Country Error state
                    is CountryState.Error -> {
                        requireContext().let { context ->
                            AlertDialog.Builder(context).apply {
                                setMessage(it.message)
                                setPositiveButton(OK, null)
                            }.show()
                        }
                    }

                    // Action performed when returns Country Failure state
                    is CountryState.Failure -> {
                        requireContext().let { context ->
                            AlertDialog.Builder(context).apply {
                                setMessage(AN_ERROR_OCCURRED + it.message)
                                setPositiveButton(OK, null)
                            }.show()
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val OK = "OK"
        const val AN_ERROR_OCCURRED = "An error occurred:"
    }
}