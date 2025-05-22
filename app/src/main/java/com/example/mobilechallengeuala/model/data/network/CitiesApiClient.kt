package com.example.mobilechallengeuala.model.data.network

import com.example.mobilechallengeuala.model.data.Constants
import com.example.mobilechallengeuala.model.data.CityModel
import retrofit2.http.GET

interface CitiesApiClient {
    @GET(Constants.PATH_CITIES)
    suspend fun getCities(): List<CityModel>

}