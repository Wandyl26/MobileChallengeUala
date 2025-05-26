package com.example.mobilechallengeuala.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.mobilechallengeuala.ui.theme.MobileChallengeUalaTheme
import com.example.mobilechallengeuala.view.composable.Greeting
import com.example.mobilechallengeuala.viewmodel.CitiesViewModel

class MainActivity : ComponentActivity() {

    val citiesViewModel: CitiesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var continueMain = false

        citiesViewModel.isTerminate.observe(this) {
            continueMain = it
        }
        citiesViewModel.getCitiesNet(this)

        installSplashScreen().apply {
            setKeepOnScreenCondition { !continueMain }
        }

        citiesViewModel.isTerminate.removeObservers(this)
        enableEdgeToEdge()
        setContent {
            MobileChallengeUalaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Cargado",
                        modifier = Modifier.padding(innerPadding)

                    )
                }
            }
        }

        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
        finish()
    }

}
