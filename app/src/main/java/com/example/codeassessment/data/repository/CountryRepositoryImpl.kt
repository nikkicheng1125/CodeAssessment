/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.data.repository

import com.example.codeassessment.data.converter.CountryConverterImpl
import com.example.codeassessment.data.countryservices.CountryApi
import com.example.codeassessment.domain.model.Country
import com.example.codeassessment.domain.repository.ICountryRepository

/**
 * [ICountryRepository] impl
 * Returns a list of Country object
 */
class CountryRepositoryImpl(private val countryApi: CountryApi) : ICountryRepository {
    override suspend fun getCountryData(): List<Country> {
        return CountryConverterImpl.convertToDomainList(countryApi.getCountries())
    }
}