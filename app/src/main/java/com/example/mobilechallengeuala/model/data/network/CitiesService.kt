package com.example.mobilechallengeuala.model.data.network

import com.example.mobilechallengeuala.model.data.CityModel

class CitiesService : RetrofitNetwork() {
    lateinit var service: CitiesApiClient

    suspend fun getCitiesApiClient(): List<CityModel> {
        service = setupRetrofit()
        return service.getCities()
    }
}