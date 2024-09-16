/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.codeassessment.data.countryservices.CountryApi
import com.example.codeassessment.data.dto.CountryResponse
import com.example.codeassessment.data.dto.Currency
import com.example.codeassessment.data.dto.Language
import com.example.codeassessment.domain.model.Country
import com.example.codeassessment.presentation.viewmodel.CountryViewModel.Companion.EMPTY_STRING
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * CountryRepositoryImpl tests
 */
class CountryRepositoryImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Mock CountryApi class
    private val countryApi = mockk<CountryApi>(relaxed = true)
    private lateinit var classUnderTest: CountryRepositoryImpl

    @Before
    fun setup() {
        //Initialize CountryRepositoryImpl class
        classUnderTest = CountryRepositoryImpl(countryApi)
    }

    @Test
    fun `Given CountriesApi returns country data`() = runTest {
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
        // Mock data for CountryResponse
        val countryResponse = CountryResponse(
            capital = EMPTY_STRING,
            code = EMPTY_STRING,
            currency = currency,
            flag = EMPTY_STRING,
            language = language,
            name = EMPTY_STRING,
            region = EMPTY_STRING
        )
        // Mock data for Country
        val expected = Country(
            capital = EMPTY_STRING,
            code = EMPTY_STRING,
            currency = currency,
            flag = EMPTY_STRING,
            language = language,
            name = EMPTY_STRING,
            region = EMPTY_STRING
        )

        // Stub CountryResponse as value returned from api call
        coEvery { countryApi.getCountries() } returns listOf(countryResponse)

        val result = classUnderTest.getCountryData()

        assertEquals(expected,result.first())
    }

    @Test
    fun `Given CountryApi returns emptyList`() = runTest {
        // Stub an empty list as value returned from api call
        coEvery { countryApi.getCountries() } returns listOf()

        val result = classUnderTest.getCountryData()

        assertTrue(result.isEmpty())
    }
}