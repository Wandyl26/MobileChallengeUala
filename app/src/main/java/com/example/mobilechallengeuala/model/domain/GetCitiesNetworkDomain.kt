package com.example.mobilechallengeuala.model.domain

import com.example.mobilechallengeuala.model.data.network.CitiesService
import javax.inject.Inject

class GetCitiesNetworkDomain @Inject constructor(private val servicio : CitiesService) {

    suspend operator fun invoke() = servicio.getCitiesApiClient()
}