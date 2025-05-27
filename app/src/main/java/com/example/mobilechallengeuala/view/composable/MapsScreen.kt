package com.example.mobilechallengeuala.view.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = cityCoordinates),
            title = nameCity,
            snippet = "Latitud: $lat, Longitud: $lng"
        )
    }
}