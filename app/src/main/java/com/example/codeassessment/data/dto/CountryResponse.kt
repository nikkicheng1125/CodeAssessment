/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * CountryResponse to receive in api call
 */
@JsonClass(generateAdapter = true)
data class CountryResponse(
    @Json(name = "capital")
    val capital: String,
    @Json(name = "code")
    val code: String,
    @Json(name = "currency")
    val currency: Currency,
    @Json(name = "flag")
    val flag: String,
    @Json(name = "language")
    val language: Language,
    @Json(name = "name")
    val name: String,
    @Json(name = "region")
    val region: String
)
