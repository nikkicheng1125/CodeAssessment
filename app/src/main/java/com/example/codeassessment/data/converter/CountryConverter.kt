/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.data.converter

/**
 * Country Converter to convert Entity class to Domain class
 */
interface CountryConverter<Entity, Domain> {
    fun convertToDomain(countryResponse: Entity): Domain
}