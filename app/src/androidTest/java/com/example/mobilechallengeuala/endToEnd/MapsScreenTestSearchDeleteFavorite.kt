package com.example.mobilechallengeuala.endToEnd

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.example.mobilechallengeuala.view.MapsActivity
import com.example.mobilechallengeuala.view.MapsActivity.Companion.searchTextScreens
import com.example.mobilechallengeuala.view.composable.SearchedCitiesNavigation
import com.example.mobilechallengeuala.viewmodel.InitCitiesViewModel
import com.example.mobilechallengeuala.viewmodel.SearchCitiesViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import kotlin.system.exitProcess

@HiltAndroidTest
class MapsScreenTestSearchDeleteFavorite {
    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MapsActivity>()

    @Inject
    lateinit var searchCitiesViewModel: SearchCitiesViewModel
    @Inject
    lateinit var initCitiesViewModelImpl: InitCitiesViewModel

    @Before
    fun setUp() {
        // Injecta Hilt dependencias antes de los tests run
        hiltRule.inject()
        initCitiesViewModelImpl.initCities()
        searchTextScreens=""
        composeTestRule.activity.setContent {
            SearchedCitiesNavigation(
                searchCitiesViewModel,
                onSearch ={
                    searchTextScreens =it
                    searchCitiesViewModel.searchCities(it)
                },
                onFavorite = {
                    it.favorite=!it.favorite
                    searchCitiesViewModel.updateCity(it, searchTextScreens)
                }
            )
        }

    }

    @After
    fun finish() {
        composeTestRule.activity.finish()
        composeTestRule.activityRule.scenario.close()
        Thread.sleep(5000)
    }


    @Test
    fun testSearchDeleteFavorite() {


        while (initCitiesViewModelImpl.isTerminate.value== null &&
            initCitiesViewModelImpl.isTerminate.value != true
        )
        {

        }
        composeTestRule.waitForIdle()
        Thread.sleep(2000)

        composeTestRule.onNodeWithText("Search")
            .performTextInput("Singapore")

        composeTestRule.waitForIdle()
        Thread.sleep(2000)

        composeTestRule.onNodeWithContentDescription("Favorite")
            .performClick()

        composeTestRule.waitForIdle()
        Thread.sleep(2000)

        composeTestRule.onNodeWithText("Singapore")
            .performTextClearance()

        composeTestRule.waitForIdle()
        Thread.sleep(2000)

        composeTestRule.onNodeWithContentDescription("Favorite")
            .performClick()

        composeTestRule.waitForIdle()
        Thread.sleep(2000)

        composeTestRule.onNodeWithContentDescription("Favorite")
            .assertIsNotDisplayed()

    }


}