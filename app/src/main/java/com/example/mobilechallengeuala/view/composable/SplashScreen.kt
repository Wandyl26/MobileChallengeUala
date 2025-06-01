package com.example.mobilechallengeuala.view.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.mobilechallengeuala.viewmodel.InitCitiesViewModel

@Composable
fun Greeting(
    citiesViewModel: InitCitiesViewModel,
    name: String,
    modifier: Modifier = Modifier,
    onCharge: (Boolean) -> Unit) {
    val isTerminate = citiesViewModel.isTerminate.observeAsState()

    LaunchedEffect(key1 =Unit) {
       citiesViewModel.initCities()
    }

    onCharge(isTerminate.value?:false)

    Text(
        text = name,
        modifier = modifier
    )
}