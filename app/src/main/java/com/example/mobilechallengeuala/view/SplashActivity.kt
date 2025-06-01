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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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
                    name = "Cargando",
                    modifier = Modifier.fillMaxSize(),
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
