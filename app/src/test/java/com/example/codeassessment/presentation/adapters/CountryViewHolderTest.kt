/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.presentation.adapters

import com.example.codeassessment.data.dto.Currency
import com.example.codeassessment.data.dto.Language
import com.example.codeassessment.databinding.CountryAdapterBinding
import com.example.codeassessment.domain.model.Country
import com.example.codeassessment.presentation.viewmodel.CountryViewModel.Companion.EMPTY_STRING
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * CountryViewHolder tests
 */
@RunWith(JUnit4::class)
class CountryViewHolderTest {

    //Mock CountryAdapterBinding
    @MockK
    private lateinit var binding: CountryAdapterBinding
    private lateinit var classUnderTest: CountryViewHolder

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        //Initialize CountryViewHolder
        classUnderTest = CountryViewHolder(binding)
    }

    @Test
    fun `test binding sets correct data to view`() {
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
        // Mock data for Country
        val country = Country(
            capital = EMPTY_STRING,
            code = EMPTY_STRING,
            currency = currency,
            flag = EMPTY_STRING,
            language = language,
            name = EMPTY_STRING,
            region = EMPTY_STRING
        )
        // Invoke bind method
        classUnderTest.bind(country)

        // Verify binding
        verify { binding.country = country }
        verify { binding.executePendingBindings() }
    }
}