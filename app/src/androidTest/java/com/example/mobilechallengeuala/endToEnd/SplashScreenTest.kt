package com.example.mobilechallengeuala.endToEnd

import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.mobilechallengeuala.view.SplashActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.example.mobilechallengeuala.view.composable.Greeting
import com.example.mobilechallengeuala.viewmodel.InitCitiesViewModel
import org.junit.After
import javax.inject.Inject

@HiltAndroidTest
class SplashScreenTest {

    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<SplashActivity>()

    @Inject
    lateinit var initCitiesViewModelImpl: InitCitiesViewModel

    @Before
    fun setUp() {
        // Injecta Hilt dependencias antes de los tests run
        hiltRule.inject()

    }

    @Test
    fun splashScreenTest() {
        //runOnUiThread or runOnIdle

        composeTestRule.activity.setContent {
            Greeting(
                initCitiesViewModelImpl,
                onCharge ={
                    assert(it)
                    composeTestRule.activity.finish()
                }
            )
        }
    }

}