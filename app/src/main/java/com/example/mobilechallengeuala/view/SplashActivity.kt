package com.example.mobilechallengeuala.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.mobilechallengeuala.ui.theme.MobileChallengeUalaTheme
import com.example.mobilechallengeuala.view.composable.Greeting
import com.example.mobilechallengeuala.viewmodel.InitCitiesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    @Inject lateinit var citiesViewModel: InitCitiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MobileChallengeUalaTheme {
                Greeting(citiesViewModel,
                    onCharge = {
                        if (it)
                        {
                            val intent = Intent(this, MapsActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                )
            }
        }
    }

}
