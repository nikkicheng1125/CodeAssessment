/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.codeassessment.data.dto.Currency
import com.example.codeassessment.data.dto.Language
import com.example.codeassessment.domain.model.Country
import com.example.codeassessment.domain.repository.ICountryRepository
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
 * CountryUseCase tests
 */
class CountryUseCaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Mock ICountryRepository
    private val repository: ICountryRepository = mockk(relaxed = true)
    private lateinit var classUnderTest: CountryUseCase

    @Before
    fun setup() {
        //Initialize CountryUseCase class
        classUnderTest = CountryUseCase(repository)
    }

    @Test
    fun `Given api return a list of Country`() = runTest {
        //Mock data for Currency
        val currency = Currency(
            code = EMPTY_STRING,
            name = EMPTY_STRING,
            symbol = EMPTY_STRING
        )
        //Mock data for Language
        val language = Language(
            code = EMPTY_STRING,
            name = EMPTY_STRING
        )
        //Mock data for a list of Country
        val expected = listOf(
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
        // Stub a list of Country as value returned from repository
        coEvery { repository.getCountryData() } returns expected

        assertTrue(classUnderTest.invoke().isNotEmpty())

        val actual = classUnderTest.invoke()

        assertEquals(expected, actual)
    }

    @Test
    fun `Given Given api return an empty list`() = runTest {
        // Stub an empty list as value returned from repository
        coEvery { repository.getCountryData() } returns emptyList()

        assertTrue(classUnderTest.invoke().isEmpty())
    }
}