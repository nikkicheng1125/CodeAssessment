/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Currency model to receive in api call
 */
@JsonClass(generateAdapter = true)
data class Currency(
    @Json(name = "code")
    val code: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "symbol")
    val symbol: String
)
