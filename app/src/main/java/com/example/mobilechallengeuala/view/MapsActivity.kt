package com.example.mobilechallengeuala.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mobilechallengeuala.model.domain.CityDomain
import com.example.mobilechallengeuala.view.composable.SearchedCitiesNavigation
import com.example.mobilechallengeuala.view.ui.theme.MobileChallengeUalaTheme
import com.example.mobilechallengeuala.viewmodel.SearchCitiesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MapsActivity @Inject constructor(): ComponentActivity() {
    companion object {
        var citySelected = CityDomain(0, "SG","Singapore",103.850067 ,1.28967, false)

        var searchTextScreens=""
    }

    @Inject lateinit var searchCitiesViewModel: SearchCitiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileChallengeUalaTheme {
                SearchedCitiesNavigation(searchCitiesViewModel,
                    onSearch = {
                        searchTextScreens = it
                        searchCitiesViewModel.searchCities(it)
                               },
                    onFavorite = {
                        it.favorite=!it.favorite
                        searchCitiesViewModel.updateCity(it, searchTextScreens)
                                 }
                )

            }
        }
    }
}

