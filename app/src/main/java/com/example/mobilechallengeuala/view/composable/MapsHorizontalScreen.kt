package com.example.mobilechallengeuala.view.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilechallengeuala.model.domain.CityDomain
import com.example.mobilechallengeuala.ui.theme.ColorBlueFavorite
import com.example.mobilechallengeuala.view.MapsActivity.Companion.citySelected
import com.example.mobilechallengeuala.view.MapsActivity.Companion.searchTextScreens
import com.example.mobilechallengeuala.viewmodel.SearchCitiesViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HorizontalScreen(
    searchCitiesViewModel: SearchCitiesViewModel,
    onSearch: (String) -> Unit,
    onFavorite: (city: CityDomain) -> Unit) {

    val listCities = searchCitiesViewModel.listCities.observeAsState()
    var expanded by rememberSaveable { mutableStateOf(false) }
    var textSearch by rememberSaveable {
        mutableStateOf(searchTextScreens)
    }
    var nameCity by rememberSaveable {
        mutableStateOf("Name: ${citySelected.name}, Country: ${citySelected.country}")
    }

    var cityCoordinates by rememberSaveable { mutableStateOf(LatLng(citySelected.lat, citySelected.lon)) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(cityCoordinates, 15f)
    }
    val marker = rememberMarkerState(position = cityCoordinates )
    var snippet by rememberSaveable { mutableStateOf("Lat: ${citySelected.lat}, Lon: ${citySelected.lon}") }

    fun reloadVar(){
        cityCoordinates=LatLng(citySelected.lat, citySelected.lon)
        marker.position = cityCoordinates
        nameCity="Name: ${citySelected.name}, Country: ${citySelected.country}"
        snippet="Lat: ${citySelected.lat}, Lon: ${citySelected.lon}"
        cameraPositionState.position =
            CameraPosition.fromLatLngZoom(cityCoordinates, 15f)

    }

    LaunchedEffect(key1 =Unit) {
        textSearch=searchTextScreens
        searchCitiesViewModel.searchCities(textSearch)
        reloadVar()
    }

    Row(Modifier
        .fillMaxWidth(0.95f)) {
        // Barra de búsqueda
        Column{
            SearchBar(
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

                ) {}
            LazyColumn{
                items(count = listCities.value?.size ?: 0) { index ->
                    val resultItem = listCities.value?.get(index)
                    ListItem(
                        headlineContent = { Text((resultItem?.name ) +", ${resultItem?.country}") },
                        supportingContent = { Text(("Lat: ${resultItem?.lat}, Lon: ${resultItem?.lon}") ,
                            fontSize = 13.sp)},
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        trailingContent ={
                            IconButton(onClick = {
                            resultItem?.let {
                                onFavorite(it) } }) {
                                Icon(
                                    rememberVectorPainter(image = Icons.Filled.Star),
                                    contentDescription = "Favorite",
                                    tint = {
                                        if (resultItem?.favorite == true)
                                            ColorBlueFavorite
                                        else
                                            Color.Gray
                                })
                            }
                        },
                        modifier = Modifier
                            .clickable {
                                resultItem?.let {
                                    citySelected = it
                                }
                                reloadVar()
                            }
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                            .fillParentMaxWidth(0.5f)
                    )
                }
            }


        }
        Column {
            // Mapa de Google
            Spacer(modifier = Modifier.width(16.dp)) // Separación entre la barra de búsqueda y el mapa

            GoogleMap(
                cameraPositionState = cameraPositionState,
                onMapClick ={
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(LatLng(citySelected.lat, citySelected.lon), 18f)
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

    }
}
