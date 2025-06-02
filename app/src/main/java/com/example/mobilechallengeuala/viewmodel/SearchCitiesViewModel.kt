package com.example.mobilechallengeuala.viewmodel

import androidx.lifecycle.LiveData
import com.example.mobilechallengeuala.model.domain.CityDomain

interface SearchCitiesViewModel {

    val listCities : LiveData<List<CityDomain>>

    fun searchCities (search:String)
    fun updateCity(city: CityDomain, search: String)
}