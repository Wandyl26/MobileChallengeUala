package com.example.mobilechallengeuala.view.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import com.example.mobilechallengeuala.model.domain.CityDomain
import com.example.mobilechallengeuala.ui.theme.ColorBlueFavorite
import com.example.mobilechallengeuala.viewmodel.SearchCitiesViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState



@Composable
fun MapsScreen(nameCity: String="Singapore, SG", lat: Double=1.28967, lng: Double=103.850067) {
    val cityCoordinates =  LatLng(lat, lng)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(cityCoordinates, 15f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,


    ) {
        Marker(
            state = MarkerState(position = cityCoordinates),
            title = nameCity,
            snippet = "Latitud: $lat, Longitud: $lng"
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizableSearchBar(
    searchCitiesViewModel: SearchCitiesViewModel,
    onSearch: (String) -> Unit,
    onFavorite: (city: CityDomain) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = { Icon(Icons.Default.Search, contentDescription = "Search") },
    modifier: Modifier = Modifier
) {

    val listCities = searchCitiesViewModel.listCities.observeAsState()
    var expanded by rememberSaveable { mutableStateOf(false) }
    var textSearch by rememberSaveable {
        mutableStateOf("")
    }

    LaunchedEffect(key1 =Unit) {
        searchCitiesViewModel.searchCities(textSearch)
    }

    Box(
        modifier
            .fillMaxWidth()
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = 0f },
            inputField = {
               SearchBarDefaults.InputField(
                    query = textSearch,
                    onQueryChange = {
                        textSearch=it
                        onSearch(textSearch) },
                    onSearch = {
                        onSearch(textSearch)
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text("Search") },
                   leadingIcon = leadingIcon,
                   )
            },
            colors = SearchBarDefaults.colors(containerColor =Color.LightGray),
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            LazyColumn {
                    items(count = listCities.value?.size ?: 0) { index ->
                        val resultText = listCities.value?.get(index)
                        ListItem(
                            headlineContent = { Text((resultText?.name ) +", ${resultText?.country}")},
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            trailingContent ={IconButton(onClick = {
                                resultText?.let {
                                    onFavorite(it) } }) {
                                Icon(rememberVectorPainter(image = Icons.Filled.Star),
                                    contentDescription = "Favorite",
                                    tint = {
                                        if (resultText?.favorite == true)
                                            ColorBlueFavorite
                                        else
                                            Color.Gray
                                    }
                                )
                            }},
                            modifier = Modifier
                                .clickable {

                                }
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp, vertical = 4.dp)
                        )
                    }
            }
        }
    }
}


