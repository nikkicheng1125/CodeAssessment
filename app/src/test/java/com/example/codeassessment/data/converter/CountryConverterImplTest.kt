/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.data.converter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.codeassessment.data.dto.CountryResponse
import com.example.codeassessment.data.dto.Currency
import com.example.codeassessment.data.dto.Language
import com.example.codeassessment.domain.model.Country
import com.example.codeassessment.presentation.viewmodel.CountryViewModel.Companion.EMPTY_STRING
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * CountryConverterImpl tests
 */
class CountryConverterImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var classUnderTest: CountryConverterImpl

    @Before
    fun setup() {
        // Initialize CountryConverterImpl class
        classUnderTest = CountryConverterImpl
    }

    @Test
    fun `Given CountryResponse convert to Country`() {
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
        // Actual data returned
        val actual = classUnderTest.convertToDomain(countryResponse)

        assertEquals(expected, actual)
    }

    @Test
    fun `Given CountryResponse convert to list of CountryResponse`() {
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
        // Actual list of Country returned
        val actual = classUnderTest.convertToDomainList(listOf(countryResponse))

        assertEquals(expected, actual.first())
    }
}