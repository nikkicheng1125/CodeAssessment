/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.codeassessment.domain.usecase.CountryUseCase

/**
 * CountryViewModel factory
 */
class CountryViewModelFactory(private val countryUseCase: CountryUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountryViewModel(countryUseCase) as T
    }
}