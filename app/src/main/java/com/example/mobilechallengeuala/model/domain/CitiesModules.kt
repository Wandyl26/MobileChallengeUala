package com.example.mobilechallengeuala.model.domain

import com.example.mobilechallengeuala.viewmodel.InitCitiesViewModel
import com.example.mobilechallengeuala.viewmodel.InitCitiesViewModelImpl
import com.example.mobilechallengeuala.viewmodel.SearchCitiesViewModel
import com.example.mobilechallengeuala.viewmodel.SearchCitiesViewModelImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 class  CitiesModules {


    @Singleton
    @Provides
    fun provideInitCitiesViewModel(
        initCitiesViewModelImpl:InitCitiesViewModelImpl
    ): InitCitiesViewModel{
        return initCitiesViewModelImpl
    }


    @Singleton
    @Provides
    fun provideSearchCitiesViewModel(
        searchCitiesViewModelImpl: SearchCitiesViewModelImpl
    ): SearchCitiesViewModel {
        return searchCitiesViewModelImpl
    }



}