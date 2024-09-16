/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.domain.repository

import com.example.codeassessment.domain.model.Country

/**
 * Country repository
 */
interface ICountryRepository {
    suspend fun getCountryData(): List<Country>
}