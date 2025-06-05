package com.example.mobilechallengeuala.endToEnd

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import com.example.mobilechallengeuala.model.domain.CityDomain
import com.example.mobilechallengeuala.view.MapsActivity
import com.example.mobilechallengeuala.view.MapsActivity.Companion.citySelected
import com.example.mobilechallengeuala.view.MapsActivity.Companion.navControllerMaps
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
class MapsScreenTestMap {
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

    @Test
    fun testMap() {

        initCitiesViewModelImpl.initCities()
        searchTextScreens =""


        while (initCitiesViewModelImpl.isTerminate.value== null &&
            initCitiesViewModelImpl.isTerminate.value != true
        )
        {

        }

        composeTestRule.waitForIdle()


        composeTestRule.onNodeWithText("Search")
            .performTextInput("bucaram")

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithContentDescription("Favorite")
            .performClick()

       composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Bucaramanga, CO")
            .performClick()


        composeTestRule.waitForIdle()

        assert(citySelected.id==3688465)
        assert(citySelected.name=="Bucaramanga")
        assert(citySelected.country=="CO")
        assert(citySelected.lat==7.12539)
        assert(citySelected.lon==-73.119797)
        assert(citySelected.favorite)
        isVerticalOrHorizontal()

        composeTestRule.onNodeWithContentDescription("Favorite")
            .performClick()

        composeTestRule.onNodeWithText("bucaram")
            .performTextClearance()



        composeTestRule.waitForIdle()

//---------------------




        composeTestRule.onNodeWithText("Search")
            .performTextInput("MEDELL")

         composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Medellin, CO")
            .performClick()


        composeTestRule.waitForIdle()


        assert(citySelected.id==3674962)
        assert(citySelected.name=="Medellin")
        assert(citySelected.country=="CO")
        assert(citySelected.lat==6.25184)
        assert(citySelected.lon==-75.563591)
        assert(!citySelected.favorite)


        isVerticalOrHorizontal()


        composeTestRule.onNodeWithText("MEDELL")
            .performTextClearance()



        composeTestRule.waitForIdle()

//---------------------




        composeTestRule.onNodeWithText("Search")
            .performTextInput("Bogota")

             composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Bogota, CO")
            .performClick()


        composeTestRule.waitForIdle()



        assert(citySelected.id==3688689)
        assert(citySelected.name=="Bogota")
        assert(citySelected.country=="CO")
        assert(citySelected.lat==4.60971)
        assert(citySelected.lon==-74.081749)
        assert(!citySelected.favorite)


        isVerticalOrHorizontal()


        composeTestRule.onNodeWithText("Bogota")
            .performTextClearance()



        composeTestRule.waitForIdle()


//---------------------


        composeTestRule.onNodeWithText("Search")
            .performTextInput("cArTaGeNa")

          composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Cartagena, PH")
            .performClick()


        composeTestRule.waitForIdle()



        assert(citySelected.id==1718247)
        assert(citySelected.name=="Cartagena")
        assert(citySelected.country=="PH")
        assert(citySelected.lat==9.82167)
        assert(citySelected.lon==122.398888)
        assert(!citySelected.favorite)


    }

    private fun isVerticalOrHorizontal(){
        if(composeTestRule.onNodeWithContentDescription("backIcon").isDisplayed()){
            composeTestRule.onNodeWithContentDescription("backIcon")
                .performClick()
            composeTestRule.waitForIdle()
        }
    }

    @After
    fun finish() {
        composeTestRule.activityRule.scenario.close()

    }

}