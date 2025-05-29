package com.example.mobilechallengeuala.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilechallengeuala.model.domain.CitiesDataBaseDomain
import com.example.mobilechallengeuala.model.domain.CityDomain
import com.example.mobilechallengeuala.model.domain.GetCitiesNetworkDomain
import com.example.mobilechallengeuala.model.domain.QueriesCitiesDataBaseDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CitiesViewModel() : ViewModel()  {
    companion object{
        private val queriesCitiesDataBaseDomain:QueriesCitiesDataBaseDomain=QueriesCitiesDataBaseDomain()
    }
    val listCities :MutableLiveData<List<CityDomain>> = MutableLiveData<List<CityDomain>>()
    var state by mutableStateOf(MutableLiveData<List<CityDomain>>())

    private var getCitiesNetworkDomain = GetCitiesNetworkDomain()
    var isTerminate = MutableLiveData<Boolean>()

    fun getCitiesNet(context: Context) {
        isTerminate.postValue(false)
        viewModelScope.launch(Dispatchers.IO) {
            CitiesDataBaseDomain.getDatabase(context)
            val result=getCitiesNetworkDomain()
            queriesCitiesDataBaseDomain.insertCities(result)
            if(result.isNotEmpty()){
                isTerminate.postValue(true)
            }
        }
    }

    fun searchCities(search:String){
        if(search.isNotEmpty())
            getSearchCities(search)
        else
            getFavoriteCities()

    }

    private fun getSearchCities(search:String){
        viewModelScope.launch(Dispatchers.IO) {
            val result = queriesCitiesDataBaseDomain.getSearchCities(search.lowercase())
            listCities.postValue(result)
           // state.value=(result)
        }
    }
    private fun getFavoriteCities(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = queriesCitiesDataBaseDomain.getFavoriteCities()
            //state.value=(result)
           listCities.postValue(result)
        }

    }
    private fun setCity(city: CityDomain, search: String ){
        viewModelScope.launch(Dispatchers.IO) {
            var result:List<CityDomain>
            city.favorite=!city.favorite
            queriesCitiesDataBaseDomain.updateCity(city)
            if(search.isNotEmpty())
                 result = queriesCitiesDataBaseDomain.getSearchCities(search.lowercase())
            else
                 result = queriesCitiesDataBaseDomain.getFavoriteCities()
            listCities.postValue(result)
        }
    }

}