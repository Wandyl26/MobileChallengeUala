package com.example.mobilechallengeuala.room.daos

import androidx.room.Room
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mobilechallengeuala.model.data.CityModel
import com.example.mobilechallengeuala.model.data.Constants.CITY_DATABASE
import com.example.mobilechallengeuala.model.data.Constants.LIMIT_SEARCH
import com.example.mobilechallengeuala.model.database.CityEntity
import com.example.mobilechallengeuala.model.database.room.CitiesDataBase
import com.example.mobilechallengeuala.model.database.room.daos.CitiesDao
import com.example.mobilechallengeuala.model.domain.convertListCitiesEntity
import com.example.mobilechallengeuala.model.domain.convertListCitiesModel
import com.google.gson.Gson
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import javax.inject.Inject
import javax.inject.Named


@RunWith(AndroidJUnit4::class)
class CitiesDaoTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: CitiesDataBase

    private lateinit var citiesDao: CitiesDao
    private lateinit var cities: MutableList<CityEntity>

    @Before
    fun initDb(){
        database= Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CitiesDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        citiesDao=database.citiesDao()
    }

    @Before
    fun createCities(){
        val context = InstrumentationRegistry.getInstrumentation().context // O el context apropiado
        val assetManager = context.assets
        var inputStream = assetManager.open("citiesOne.json")
        var jsonString= inputStream.bufferedReader().use { it.readText() }
        inputStream = assetManager.open("citiesTwo.json")
        jsonString += inputStream.bufferedReader().use { it.readText() }
        val gson= Gson()
        val citiesModel = gson.fromJson(jsonString, Array<CityModel>::class.java).toList().toMutableList()
        cities = convertListCitiesModel(citiesModel)
    }

    @Test
    fun testLimitGetSearchCities()= runTest{

        val citiesEntity = mutableListOf<CityEntity>()
        lateinit var searchCities:List<CityEntity>


        citiesDao.insertIfNotExist(cities)
        searchCities=citiesDao.getSearchCities("l".lowercase())

        citiesEntity.add(CityEntity(7287676, "CH", "L'Abbaye", 6.30266, 46.63562, false))
        citiesEntity.add(CityEntity(3008452, "FR", "L'Allier", 1.21667, 48.01667, false))
        citiesEntity.add(CityEntity(6049524, "CA", "L'Annonciation", -74.870956, 46.40839, false))
        citiesEntity.add(CityEntity(6049580, "CA", "L'Anse-au-Loup", -56.83139, 51.5168, false))
        citiesEntity.add(CityEntity(6541999, "IT", "L'Aquila", 13.39349, 42.36504, false))
        citiesEntity.add(CityEntity(3129793, "ES", "L'Arboç", 1.6, 41.26667, false))
        citiesEntity.add(CityEntity(3006952, "FR", "L'Argillier", 2.995, 45.39072, false))
        citiesEntity.add(CityEntity(6050215, "CA", "L'Ascension-de-Notre-Seigneur", -71.682404, 48.700089, false))
        citiesEntity.add(CityEntity(2793212, "BE", "L'Aulnois", 4.33873, 50.505402, false))
        citiesEntity.add(CityEntity(3006023, "FR", "L'Aunay", 0.37585, 48.732658, false))
        citiesEntity.add(CityEntity(3004582, "FR", "L'Eclause", 2.51667, 45.883331, false))
        citiesEntity.add(CityEntity(6690287, "RE", "L'Entre-Deux", 55.466671, -21.23333, false))
        citiesEntity.add(CityEntity(2659972, "CH", "L'Etivaz", 7.15, 46.416672, false))
        citiesEntity.add(CityEntity(3231369, "FR", "L'Eure", 0.13472, 49.484718, false))
        citiesEntity.add(CityEntity(6614252, "FR", "L'Haÿ-les-Roses", 2.33333, 48.783329, false))
        citiesEntity.add(CityEntity(2998628, "FR", "L'Herbe", -1.23637, 44.692242, false))
        citiesEntity.add(CityEntity(2998605, "FR", "L'Hermitière", 0.64487, 48.281879, false))
        citiesEntity.add(CityEntity(6615036, "FR", "L'Hermitière", 0.65, 48.283329, false))
        citiesEntity.add(CityEntity(2998554, "FR", "L'Hospitalet-près-l'Andorre", 1.79931, 42.589142, false))
        citiesEntity.add(CityEntity(6618222, "FR", "L'Hospitalet-près-l'Andorre", 1.8035, 42.590698, false))


        assertEquals(searchCities.size, LIMIT_SEARCH)

        for (i in searchCities.indices){
            assertEquals(searchCities[i].country, citiesEntity[i].country)
            assertEquals(searchCities[i].name, citiesEntity[i].name)
            assertEquals(searchCities[i].id, citiesEntity[i].id)
            assertEquals(searchCities[i].lon, citiesEntity[i].lon,0.0)
            assertEquals(searchCities[i].lat, citiesEntity[i].lat,0.0)
            assertEquals(searchCities[i].favorite, citiesEntity[i].favorite)
        }



    }



    @Test
    fun testGetFavoritesCities()= runTest{

        val citiesEntity = mutableListOf<CityEntity>()
        lateinit var favoriteCities:List<CityEntity>

        citiesEntity.add(CityEntity(2793212, "BE", "L'Aulnois", 4.33873, 50.505402, true))
        citiesEntity.add(CityEntity(3006023, "FR", "L'Aunay", 0.37585, 48.732658, true))
        citiesEntity.add(CityEntity(3004582, "FR", "L'Eclause", 2.51667, 45.883331, true))
        citiesEntity.add(CityEntity(6690287, "RE", "L'Entre-Deux", 55.466671, -21.23333, true))
        citiesEntity.add(CityEntity(2659972, "CH", "L'Etivaz", 7.15, 46.416672, true))
        citiesEntity.add(CityEntity(3231369, "FR", "L'Eure", 0.13472, 49.484718, true))
        citiesEntity.add(CityEntity(6614252, "FR", "L'Haÿ-les-Roses", 2.33333, 48.783329, true))

        citiesDao.insertIfNotExist(cities)
        for (i in citiesEntity.indices){
            citiesDao.updateCity(citiesEntity[i])
        }
        favoriteCities=citiesDao.getFavoriteCities()

        for (i in favoriteCities.indices){
            assertEquals(favoriteCities[i].country, citiesEntity[i].country)
            assertEquals(favoriteCities[i].name, citiesEntity[i].name)
            assertEquals(favoriteCities[i].id, citiesEntity[i].id)
            assertEquals(favoriteCities[i].lon, citiesEntity[i].lon,0.0)
            assertEquals(favoriteCities[i].lat, citiesEntity[i].lat,0.0)
            assertEquals(favoriteCities[i].favorite, citiesEntity[i].favorite)
        }

    }

    @Test
    fun testInsertIfNotExist()= runTest{

        val citiesEntity = mutableListOf<CityEntity>()
        lateinit var favoriteCities:List<CityEntity>

        citiesEntity.add(CityEntity(2793212, "BE", "L'Aulnois", 4.33873, 50.505402, true))
        citiesEntity.add(CityEntity(3006023, "FR", "L'Aunay", 0.37585, 48.732658, true))
        citiesEntity.add(CityEntity(3004582, "FR", "L'Eclause", 2.51667, 45.883331, true))
        citiesEntity.add(CityEntity(6690287, "RE", "L'Entre-Deux", 55.466671, -21.23333, true))
        citiesEntity.add(CityEntity(2659972, "CH", "L'Etivaz", 7.15, 46.416672, true))
        citiesEntity.add(CityEntity(3231369, "FR", "L'Eure", 0.13472, 49.484718, true))
        citiesEntity.add(CityEntity(6614252, "FR", "L'Haÿ-les-Roses", 2.33333, 48.783329, true))

        citiesDao.insertIfNotExist(cities)
        for (i in citiesEntity.indices){
            citiesDao.updateCity(citiesEntity[i])
        }
        citiesDao.insertIfNotExist(cities)
        favoriteCities=citiesDao.getFavoriteCities()

        for (i in favoriteCities.indices){
            assertEquals(favoriteCities[i].country, citiesEntity[i].country)
            assertEquals(favoriteCities[i].name, citiesEntity[i].name)
            assertEquals(favoriteCities[i].id, citiesEntity[i].id)
            assertEquals(favoriteCities[i].lon, citiesEntity[i].lon,0.0)
            assertEquals(favoriteCities[i].lat, citiesEntity[i].lat,0.0)
            assertEquals(favoriteCities[i].favorite, citiesEntity[i].favorite)
        }

    }

    @After
    fun closeDb(){
        database.close()
    }


}