/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.codeassessment.databinding.CountryAdapterBinding
import com.example.codeassessment.domain.model.Country

/**
 * RecyclerView ViewHolder to bind Country data
 */
class CountryViewHolder(private val binding: CountryAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(country: Country) {
        binding.country = country
        binding.executePendingBindings()
    }
}