package com.example.mobilechallengeuala.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilechallengeuala.model.data.LocationsModel
import com.example.mobilechallengeuala.model.domain.GetCitiesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CitiesViewModel : ViewModel()  {

    val locationsModel = MutableLiveData<List<LocationsModel>>()
    private var getCitiesUseCase = GetCitiesUseCase()
    var isTerminate = MutableLiveData<Boolean>()

    fun updateCities() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getCitiesUseCase()
            isTerminate.postValue(false)

            if(result.isNotEmpty()){
                isTerminate.postValue(true)
            }
        }
    }
}