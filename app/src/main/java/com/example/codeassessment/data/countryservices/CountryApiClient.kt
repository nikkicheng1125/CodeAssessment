/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.data.countryservices

import com.example.codeassessment.data.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Returns Retrofit client
 */
object CountryApiClient {
    fun getCountryApiClient(): CountryApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryApi::class.java)
    }
}