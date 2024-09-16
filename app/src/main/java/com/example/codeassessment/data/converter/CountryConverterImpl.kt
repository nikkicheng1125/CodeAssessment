package com.example.codeassessment.data.converter

import com.example.codeassessment.data.dto.CountryResponse
/**
 * Created by Yiwen Cheng on 09/16/2024
 */
import com.example.codeassessment.domain.model.Country

/**
 * [CountryConverter] impl
 */
object CountryConverterImpl : CountryConverter<CountryResponse, Country> {
    // Convert CountryResponse class to Country class
    override fun convertToDomain(countryResponse: CountryResponse): Country {
        return Country(
            capital = countryResponse.capital,
            code = countryResponse.code,
            currency = countryResponse.currency,
            flag = countryResponse.flag,
            language = countryResponse.language,
            name = countryResponse.name,
            region = countryResponse.region
        )
    }

    // Convert each object in list of CountryResponse to Country
    fun convertToDomainList(countryResponse: List<CountryResponse>) =
        countryResponse.map { response ->
            convertToDomain(response)
        }
}