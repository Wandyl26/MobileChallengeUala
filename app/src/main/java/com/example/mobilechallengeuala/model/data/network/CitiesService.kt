package com.example.mobilechallengeuala.model.data.network

import com.example.mobilechallengeuala.model.data.CityModel
import javax.inject.Inject

class CitiesService @Inject constructor(private val service: CitiesApiClient){

    suspend fun getCitiesApiClient(): List<CityModel> {
        return service.getCities()
    }
}