package com.example.mobilechallengeuala.endToEnd

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
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

@HiltAndroidTest
class MapsScreenTestSearch {
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
                    searchTextScreens=it
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
        composeTestRule.activityRule.scenario.close()
    }

    @Test
    fun testSearch() {

        while (initCitiesViewModelImpl.isTerminate.value== null &&
            initCitiesViewModelImpl.isTerminate.value != true
        )
        {

        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Search")
            .performTextInput("Singapore")

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Singapore, SG")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Lat: 1.28967, Lon: 103.850067")
            .assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Favorite")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Singapore")
            .performTextClearance()

        composeTestRule.waitForIdle()

        //Buscar por l

        composeTestRule.onNodeWithText("Search")
            .performTextInput("l")

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("L'Abbaye, CH")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Lat: 46.63562, Lon: 6.30266")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("L'Allier, FR")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Lat: 48.01667, Lon: 1.21667")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("L'Annonciation, CA")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Lat: 46.40839, Lon: -74.870956")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("L'Aquila, IT")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Lat: 42.36504, Lon: 13.39349")
            .assertIsDisplayed()


        composeTestRule.onNodeWithText("l")
            .performTextClearance()

        composeTestRule.waitForIdle()

        //Busquedas invalidas

        composeTestRule.onNodeWithText("Search")
            .performTextInput("Bucartail")

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithContentDescription("Favorite")
            .assertIsNotDisplayed()



        composeTestRule.onNodeWithText("Bucartail")
            .performTextReplacement("bPoskaq")

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithContentDescription("Favorite")
            .assertIsNotDisplayed()



        composeTestRule.onNodeWithText("bPoskaq")
            .performTextReplacement("Postaies")

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithContentDescription("Favorite")
            .assertIsNotDisplayed()





        composeTestRule.onNodeWithText("Postaies")
            .performTextReplacement("Venzalasut")

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithContentDescription("Favorite")
            .assertIsNotDisplayed()


        composeTestRule.onNodeWithText("Venzalasut")
            .performTextClearance()


        // Diferentes busquedas

        composeTestRule.onNodeWithText("Search")
            .performTextInput("bucara")

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Bucaramanga, CO")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Lat: 7.12539, Lon: -73.119797")
            .assertIsDisplayed()


        composeTestRule.onNodeWithText("bucara")
            .performTextReplacement("MEDELL")

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Medellin, CO")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Lat: 6.25184, Lon: -75.563591")
            .assertIsDisplayed()


        composeTestRule.onNodeWithText("MEDELL")
            .performTextReplacement("Bogota")

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Bogota, CO")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Lat: 4.60971, Lon: -74.081749")
            .assertIsDisplayed()


        composeTestRule.onNodeWithText("Bogota")
            .performTextReplacement("cArTaGeNa")

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Cartagena, PH")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Lat: 9.82167, Lon: 122.398888")
            .assertIsDisplayed()



    }


}