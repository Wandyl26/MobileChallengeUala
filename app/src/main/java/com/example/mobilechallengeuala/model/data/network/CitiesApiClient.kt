package com.example.mobilechallengeuala.model

import com.example.mobilechallengeuala.model.data.Constants
import com.example.mobilechallengeuala.model.data.LocationsModel
import retrofit2.http.GET

interface CitiesService {
    @GET(Constants.PATH_CITIES)
    suspend fun getCities(): List<LocationsModel>

}