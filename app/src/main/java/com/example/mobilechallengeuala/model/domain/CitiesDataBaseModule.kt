package com.example.mobilechallengeuala.model.domain

import android.content.Context
import androidx.room.Room
import com.example.mobilechallengeuala.model.data.Constants.CITY_DATABASE
import com.example.mobilechallengeuala.model.database.CityEntity
import com.example.mobilechallengeuala.model.database.room.CitiesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CitiesDataBaseModule {


    @Provides
    @Singleton
    fun provideCitiesDataBase(@ApplicationContext context: Context): CitiesDataBase = Room.databaseBuilder(
        context, CitiesDataBase::class.java, CITY_DATABASE)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: CitiesDataBase) = db.citiesDao()

    @Provides
    fun provideEntity() = CityEntity()

}