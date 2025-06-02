package com.example.mobilechallengeuala.viewmodel

import androidx.lifecycle.LiveData

interface InitCitiesViewModel {
    val isTerminate : LiveData<Boolean>

    fun initCities()
}