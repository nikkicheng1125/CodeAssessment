/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.codeassessment.R
import com.example.codeassessment.data.countryservices.CountryApiClient
import com.example.codeassessment.data.repository.CountryRepositoryImpl
import com.example.codeassessment.data.utils.isInternetConnected
import com.example.codeassessment.databinding.ActivityMainBinding
import com.example.codeassessment.domain.usecase.CountryUseCase
import com.example.codeassessment.presentation.viewmodel.CountryViewModel
import com.example.codeassessment.presentation.viewmodel.CountryViewModelFactory

class MainActivity : AppCompatActivity() {
    // Initialize CountryViewModel
    private val viewModel: CountryViewModel by viewModels {
        val countryApi = CountryApiClient.getCountryApiClient()
        val countryRepository = CountryRepositoryImpl(countryApi)
        val countryUseCase = CountryUseCase(countryRepository)
        CountryViewModelFactory(countryUseCase)
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //View binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        if (isInternetConnected(this)) {
            viewModel.invoke() // Invoke CountryViewModel when having internet connection
        } else {
            // Display error message when do not have internet connection
            Toast.makeText(this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show()
        }

        // Add fragment to activity
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, CountryFragment())
            .commit()
    }
}