/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.domain.usecase

import com.example.codeassessment.domain.model.Country
import com.example.codeassessment.domain.repository.ICountryRepository

/**
 * Country use case
 * Returns a list of Country
 */
class CountryUseCase(private val repository: ICountryRepository) {
    suspend operator fun invoke(): List<Country> = repository.getCountryData()
}