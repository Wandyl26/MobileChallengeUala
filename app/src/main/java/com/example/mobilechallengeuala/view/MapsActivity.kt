package com.example.mobilechallengeuala.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.mobilechallengeuala.view.composable.CustomizableSearchBar
import com.example.mobilechallengeuala.view.ui.theme.MobileChallengeUalaTheme
import com.example.mobilechallengeuala.viewmodel.CitiesViewModel

class MapsActivity : ComponentActivity() {

    private val citiesViewModel: CitiesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileChallengeUalaTheme {
                CustomizableSearchBar(citiesViewModel, onSearch = {
                    citiesViewModel.searchCities(it)
                })

            }
        }
    }
}

