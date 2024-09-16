/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.domain.model

/**
 * Country state model
 */
sealed class CountryState {
    /**
     * Data fetch success state
     */
    data class Success(
        val country: List<Country>
    ) : CountryState()

    /**
     * Data fetch error state
     */
    data class Error(
        val message: String
    ) : CountryState()

    /**
     * Network call failure state
     */
    data class Failure(
        val message: String
    ) : CountryState()
}