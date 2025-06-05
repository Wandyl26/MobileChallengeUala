package com.example.mobilechallengeuala.view.composable

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import com.example.mobilechallengeuala.R
import com.example.mobilechallengeuala.viewmodel.InitCitiesViewModel

@Composable
fun Greeting(
    citiesViewModel: InitCitiesViewModel,
    onCharge: (Boolean) -> Unit) {
    val isTerminate = citiesViewModel.isTerminate.observeAsState()
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = 5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "scale"
    )
    LaunchedEffect(key1 =Unit) {
       citiesViewModel.initCities()
    }
    if(isTerminate.value == true) {
        onCharge(isTerminate.value ?: false)
    }
    Box(modifier =  Modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(id = R.drawable.icon_uala),
            contentDescription = "Logo",
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.25f)
                .fillMaxHeight(0.25f)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin.Center
                }
        )
    }
}