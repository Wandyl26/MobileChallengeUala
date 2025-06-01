package com.example.mobilechallengeuala.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mobilechallengeuala.view.composable.CustomizableSearchBar
import com.example.mobilechallengeuala.view.ui.theme.MobileChallengeUalaTheme
import com.example.mobilechallengeuala.viewmodel.SearchCitiesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MapsActivity @Inject constructor(): ComponentActivity() {

    @Inject lateinit var searchCitiesViewModel: SearchCitiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileChallengeUalaTheme {
                CustomizableSearchBar(searchCitiesViewModel, onSearch = {
                    searchCitiesViewModel.searchCities(it)
                })

            }
        }
    }
}

