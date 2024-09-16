/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.data.countryservices

import com.example.codeassessment.data.dto.CountryResponse
import com.example.codeassessment.data.utils.Constants.END_POINT
import retrofit2.http.GET

/**
 * Country api to perform internet call
 */
interface CountryApi {
    @GET(END_POINT)
    suspend fun getCountries(): List<CountryResponse>
}