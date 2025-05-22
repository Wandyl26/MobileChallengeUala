package com.example.mobilechallengeuala.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilechallengeuala.model.data.CityModel
import com.example.mobilechallengeuala.model.domain.CitiesDataBaseDomain
import com.example.mobilechallengeuala.model.domain.GetCitiesNetworkDomain
import com.example.mobilechallengeuala.model.domain.QueriesCitiesDataBaseDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CitiesViewModel() : ViewModel()  {
    companion object{
        private val queriesCitiesDataBaseDomain:QueriesCitiesDataBaseDomain=QueriesCitiesDataBaseDomain()
    }

    val cityModel = MutableLiveData<List<CityModel>>()
    private var getCitiesNetworkDomain = GetCitiesNetworkDomain()
    var isTerminate = MutableLiveData<Boolean>()

    fun getCitiesNet(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            CitiesDataBaseDomain.getDatabase(context)
            val result = getCitiesNetworkDomain()
            isTerminate.postValue(false)
            cityModel.postValue(result)
            queriesCitiesDataBaseDomain.insertCities(result)
            if(result.isNotEmpty()){
                isTerminate.postValue(true)
            }
        }
    }
    fun getSearchCities(search:String){
        viewModelScope.launch(Dispatchers.IO) {
            val result = queriesCitiesDataBaseDomain.getSearchCities(search.lowercase())
            cityModel.postValue(result)
        }
    }
    fun getFavoriteCities(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = queriesCitiesDataBaseDomain.getFavoriteCities()
            cityModel.postValue(result)
        }

    }
    fun setCity(city: CityModel, isFavorite: Boolean ){
        viewModelScope.launch(Dispatchers.IO) {
            isTerminate.postValue(false)
            queriesCitiesDataBaseDomain.updateCity(city, isFavorite)
            isTerminate.postValue(true)
        }
    }

}