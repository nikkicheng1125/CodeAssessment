/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeassessment.data.dto.Currency
import com.example.codeassessment.data.dto.Language
import com.example.codeassessment.domain.model.Country
import com.example.codeassessment.domain.model.CountryState
import com.example.codeassessment.domain.usecase.CountryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


/**
 * CountryViewModel implementation
 */
class CountryViewModel(private val useCase: CountryUseCase) : ViewModel() {

    // Backing property for Currency
    private val _currency = Currency(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING)

    // Backing property for Language
    private val _language = Language(EMPTY_STRING, EMPTY_STRING)

    // Backing property for Country
    private val _country = Country(
        EMPTY_STRING,
        EMPTY_STRING,
        _currency,
        EMPTY_STRING,
        _language,
        EMPTY_STRING,
        EMPTY_STRING
    )

    // Backing property for CountryState
    private val _countryState = MutableStateFlow<CountryState>(
        CountryState.Success(listOf(_country))
    )

    // Flow of CountryState
    val countryState: Flow<CountryState> = _countryState

    init {
        invoke()
    }

    fun invoke() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                // Fetch Country data
                val countryList = useCase.invoke()
                if (countryList.isEmpty()) {
                    _countryState.emit(CountryState.Error(EMPTY_LIST)) // Country data fetch on Error state
                } else {
                    _countryState.emit(CountryState.Success(countryList)) // Country data fetch on Success state
                }
            }.onFailure { error ->
                _countryState.emit(CountryState.Failure(error.message.toString())) // Country data fetch on Failure state
            }
        }
    }

    companion object {
        const val EMPTY_STRING = ""
        const val EMPTY_LIST = "Empty list"
    }
}