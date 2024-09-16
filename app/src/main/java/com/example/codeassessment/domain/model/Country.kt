/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.domain.model

import com.example.codeassessment.data.dto.Currency
import com.example.codeassessment.data.dto.Language

/**
 * Country model
 */
data class Country(
    val capital: String,
    val code: String,
    val currency: Currency,
    val flag: String,
    val language: Language,
    val name: String,
    val region: String
)
