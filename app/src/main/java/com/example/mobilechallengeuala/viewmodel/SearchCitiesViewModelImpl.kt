package com.example.mobilechallengeuala.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilechallengeuala.model.domain.CityDomain
import com.example.mobilechallengeuala.model.domain.QueriesCitiesDataBaseDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchCitiesViewModelImpl @Inject constructor(
    private val queriesCitiesDataBaseDomain: QueriesCitiesDataBaseDomain
) : SearchCitiesViewModel, ViewModel() {

    val _listCities : MutableLiveData<List<CityDomain>> = MutableLiveData<List<CityDomain>>()
    override val listCities : LiveData<List<CityDomain>>
        get() = _listCities

    override fun searchCities(search:String){
        if(search.isNotEmpty())
            getSearchCities(search)
        else
            getFavoriteCities()


    }

    private fun getSearchCities(search:String){
        viewModelScope.launch(Dispatchers.IO) {
            val result = queriesCitiesDataBaseDomain.getSearchCities(search.lowercase())
            _listCities.postValue(result)
        }
    }
    private fun getFavoriteCities(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = queriesCitiesDataBaseDomain.getFavoriteCities()
            _listCities.postValue(result)
        }

    }
    override fun updateCity(city: CityDomain, search: String ){
        viewModelScope.launch(Dispatchers.IO) {
            var result:List<CityDomain>
            queriesCitiesDataBaseDomain.updateCity(city)
            if(search.isNotEmpty())
                result = queriesCitiesDataBaseDomain.getSearchCities(search.lowercase())
            else
                result = queriesCitiesDataBaseDomain.getFavoriteCities()
            _listCities.postValue(result)
        }
    }

}