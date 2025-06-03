package com.example.mobilechallengeuala.view.composable

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobilechallengeuala.model.domain.CityDomain
import com.example.mobilechallengeuala.viewmodel.SearchCitiesViewModel



@Composable
fun SearchedCitiesNavigation(
    searchCitiesViewModel: SearchCitiesViewModel,
    onSearch: (String) -> Unit,
    onFavorite: (city: CityDomain) -> Unit) {
    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "search") {
                composable("map") { MapsScreen(navController = navController) }
                composable("search") { CustomizableSearchBar(searchCitiesViewModel, onSearch, onFavorite, navController)}
            }
        }
        Configuration.ORIENTATION_LANDSCAPE -> {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "searchMap") {
                composable("searchMap") { HorizontalScreen(searchCitiesViewModel, onSearch, onFavorite) }
            }
        }

    }
}

