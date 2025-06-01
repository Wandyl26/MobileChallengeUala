package com.example.mobilechallengeuala.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilechallengeuala.model.domain.CityDomain
import com.example.mobilechallengeuala.model.domain.GetCitiesNetworkDomain
import com.example.mobilechallengeuala.model.domain.QueriesCitiesDataBaseDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class InitCitiesViewModelImpl @Inject constructor(
    private val queriesCitiesDataBaseDomain:QueriesCitiesDataBaseDomain,
    private val getCitiesNetworkDomain : GetCitiesNetworkDomain
) : InitCitiesViewModel, ViewModel()  {

    var _isTerminate = MutableLiveData<Boolean>()

    override val isTerminate : LiveData<Boolean>
                 get() = _isTerminate

    override fun initCities() {
        viewModelScope.launch(Dispatchers.IO){
            val result=getCitiesNetworkDomain()
            queriesCitiesDataBaseDomain.insertCities(result)
            if(result.isNotEmpty()){
                _isTerminate.postValue(true)
            }
        }
    }

}