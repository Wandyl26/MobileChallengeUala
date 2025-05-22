package com.example.mobilechallengeuala.model.data.network

import com.example.mobilechallengeuala.model.data.LocationsModel

class CitiesService : RetrofitNetwork() {
    lateinit var service: CitiesApiClient

    suspend fun getCitiesApiClient(): List<LocationsModel> {
        service = setupRetrofit()
        return service.getCities()
    }
}