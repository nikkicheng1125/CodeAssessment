/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.codeassessment.data.dto.Currency
import com.example.codeassessment.data.dto.Language
import com.example.codeassessment.domain.model.Country
import com.example.codeassessment.domain.model.CountryState
import com.example.codeassessment.domain.usecase.CountryUseCase
import com.example.codeassessment.presentation.viewmodel.CountryViewModel.Companion.EMPTY_STRING
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Rule
import org.junit.Test

/**
 * CountryViewModel tests
 */
@OptIn(ExperimentalCoroutinesApi::class)
class CountryViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Mock CountryUseCase
    private val useCase: CountryUseCase = mockk()
    private lateinit var classUnderTest: CountryViewModel

    @After
    fun reset() {
        //Reset Dispatcher to Main after tests
        Dispatchers.resetMain()
    }

    @Test
    fun `loadCountries should emit Success state when countries are fetched successfully`(): Unit =
        runTest {
            // Mock data for Currency
            val currency = Currency(
                code = EMPTY_STRING,
                name = EMPTY_STRING,
                symbol = EMPTY_STRING
            )
            // Mock data for Language
            val language = Language(
                code = EMPTY_STRING,
                name = EMPTY_STRING
            )
            // Mock data for a list of Country
            val countryList = listOf(
                Country(
                    capital = EMPTY_STRING,
                    code = EMPTY_STRING,
                    currency = currency,
                    flag = EMPTY_STRING,
                    language = language,
                    name = EMPTY_STRING,
                    region = EMPTY_STRING
                )
            )
            // Stub a list of Country as value returned from useCase
            coEvery { useCase() } returns countryList

            // Initialize CountryViewModel
            classUnderTest = CountryViewModel(useCase)

            advanceUntilIdle()

            //Actual result returned
            val countryState = classUnderTest.countryState.take(1).toList()
            assertEquals(
                CountryState.Success(countryList), //expected
                countryState.first() //actual
            )
        }

    @Test
    fun `loadCountries should emit Error state when country list is empty`() = runTest {
        // Stub an empty list as value returned from useCase
        coEvery { useCase() } returns emptyList()

        // Initialize CountryViewModel
        classUnderTest = CountryViewModel(useCase)

        advanceUntilIdle()

        // Actual result returned
        val countryState = classUnderTest.countryState.take(1).toList()

        assertEquals(
            CountryState.Error("Empty list"), //expected
            countryState.first() //actual
        )
    }

    @Test
    fun `loadCountries should emit Failure state when an exception is thrown`() = runTest {
        val exception = RuntimeException("Network error")
        // Stub an exception as value returned from useCase
        coEvery { useCase() } throws exception

        // Initialize CountryViewModel
        classUnderTest = CountryViewModel(useCase)

        advanceUntilIdle()

        // Actual result returned
        val countryState = classUnderTest.countryState.take(1).toList()
        assertEquals(CountryState.Failure(
            exception.message.toString()), // expected
            countryState.first() //actual
        )
    }
}