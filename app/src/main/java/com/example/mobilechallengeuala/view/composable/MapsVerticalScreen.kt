package com.example.mobilechallengeuala.view.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobilechallengeuala.model.domain.CityDomain
import com.example.mobilechallengeuala.ui.theme.ColorBlueFavorite
import com.example.mobilechallengeuala.view.MapsActivity.Companion.citySelected
import com.example.mobilechallengeuala.view.MapsActivity.Companion.navControllerMaps
import com.example.mobilechallengeuala.view.MapsActivity.Companion.searchTextScreens
import com.example.mobilechallengeuala.viewmodel.SearchCitiesViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsScreen(navController: NavController
) {
    var cityCoordinates by rememberSaveable { mutableStateOf(LatLng(citySelected.lat, citySelected.lon)) }
    var nameCity by rememberSaveable {
        mutableStateOf("Name: ${citySelected.name}, Country: ${citySelected.country}")
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(cityCoordinates, 15f)
    }
    val marker = rememberMarkerState(position = cityCoordinates )
    var snippet by rememberSaveable { mutableStateOf("Lat: ${citySelected.lat}, Lon: ${citySelected.lon}") }

    LaunchedEffect(key1 =Unit) {
        cityCoordinates=LatLng(citySelected.lat, citySelected.lon)
        marker.position = cityCoordinates
        nameCity="Name: ${citySelected.name}, Country: ${citySelected.country}"
        snippet="Lat: ${citySelected.lat}, Lon: ${citySelected.lon}"
        cameraPositionState.position =
            CameraPosition.fromLatLngZoom(cityCoordinates, 15f)
    }
    Column(
        modifier = Modifier
            .fillMaxHeight(0.95f)) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Back") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, "backIcon")
                        }
                    },
                    //windowInsets = WindowInsets.safeContent
                )

            },
            content = {
                GoogleMap(
                    cameraPositionState = cameraPositionState,
                    onMapClick ={
                        cameraPositionState.position = CameraPosition.fromLatLngZoom(cityCoordinates, 18f)
                    }

                ) {
                    Marker(
                        state = marker,
                        title = nameCity,
                        snippet = snippet
                    )
                    marker.showInfoWindow()
                }
            }
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizableSearchBar(
    searchCitiesViewModel: SearchCitiesViewModel,
    onSearch: (String) -> Unit,
    onFavorite: (city: CityDomain) -> Unit,
    navController: NavController
) {

    val listCities = searchCitiesViewModel.listCities.observeAsState()
    var expanded by rememberSaveable { mutableStateOf(false) }
    var textSearch by rememberSaveable {
        mutableStateOf(searchTextScreens)
    }
    navControllerMaps=navController

    LaunchedEffect(key1 =Unit) {
        textSearch=searchTextScreens
        searchCitiesViewModel.searchCities(textSearch)
    }

    Row{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
        ){

            SearchBar(
                modifier = Modifier
                    .semantics { traversalIndex = 0f },
                inputField = {
                    SearchBarDefaults.InputField(
                        query = textSearch,
                        onQueryChange = {
                            textSearch=it
                            searchTextScreens=it
                            onSearch(textSearch) },
                        onSearch = {
                            onSearch(textSearch)
                        },
                        expanded = expanded,
                        onExpandedChange = { expanded = false },
                        placeholder = { Text("Search") },
                        leadingIcon = {
                            if(!expanded)  Icon(Icons.Default.Search, contentDescription = "Search")
                            else Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") },
                    )
                },
                colors = SearchBarDefaults.colors(containerColor = Color.LightGray),
                expanded = expanded,
                onExpandedChange = { expanded = false },

                ) {
            }

            LazyColumn {
                items(count = listCities.value?.size ?: 0) { index ->
                    val resultItem = listCities.value?.get(index)
                    ListItem(
                        headlineContent = { Text((resultItem?.name ) +", ${resultItem?.country}")},
                       supportingContent = { Text(("Lat: ${resultItem?.lat}, Lon: ${resultItem?.lon}") ,
                           fontSize = 13.sp)},
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        trailingContent ={
                            IconButton(onClick = {
                                resultItem?.let {
                                    onFavorite(it)
                                } }) {
                                Icon(
                                    rememberVectorPainter(image = Icons.Filled.Star),
                                    contentDescription = "Favorite",
                                    tint = {
                                        if (resultItem?.favorite == true)
                                            ColorBlueFavorite
                                        else
                                            Color.Gray
                                    }
                                )
                            }
                        },
                        modifier = Modifier
                            .clickable {
                                resultItem?.let {
                                    citySelected = it
                                }
                                navController.navigate("map")
                            }
                            .padding(horizontal = 6.dp, vertical = 6.dp)
                    )
                }
            }

        }
    }
}
