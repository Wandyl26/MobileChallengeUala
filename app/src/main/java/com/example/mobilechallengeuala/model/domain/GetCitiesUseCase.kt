package com.example.mobilechallengeuala.model.domain

import com.example.mobilechallengeuala.model.data.network.CitiesService

class GetCitiesUseCase {

    private val servicio = CitiesService()

    suspend operator fun invoke() = servicio.getCitiesApiClient()
}