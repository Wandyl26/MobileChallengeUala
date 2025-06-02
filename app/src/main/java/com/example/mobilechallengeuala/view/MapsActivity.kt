package com.example.mobilechallengeuala.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mobilechallengeuala.view.composable.CustomizableSearchBar
import com.example.mobilechallengeuala.view.composable.SearchedCitiesNavigation
import com.example.mobilechallengeuala.view.ui.theme.MobileChallengeUalaTheme
import com.example.mobilechallengeuala.viewmodel.SearchCitiesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MapsActivity @Inject constructor(): ComponentActivity() {

    @Inject lateinit var searchCitiesViewModel: SearchCitiesViewModel
    private var searchText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileChallengeUalaTheme {
                SearchedCitiesNavigation(searchCitiesViewModel,
                    onSearch = {
                        searchText = it
                        searchCitiesViewModel.searchCities(it)
                               },
                    onFavorite = {
                        it.favorite=!it.favorite
                        searchCitiesViewModel.updateCity(it, searchText)
                                 }
                )

            }
        }
    }
}

