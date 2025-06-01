package com.example.mobilechallengeuala.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface InitCitiesViewModel {
    val isTerminate : LiveData<Boolean>

    fun initCities()
}