/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codeassessment.databinding.CountryAdapterBinding
import com.example.codeassessment.domain.model.Country

/**
 * RecyclerView adapter to display a list of Country
 */
class CountryAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<CountryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = CountryAdapterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int = countries.size
}