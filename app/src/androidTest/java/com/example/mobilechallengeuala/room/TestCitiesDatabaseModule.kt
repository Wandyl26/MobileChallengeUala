package com.example.mobilechallengeuala.room

import android.content.Context
import androidx.room.Room
import com.example.mobilechallengeuala.model.data.Constants.CITY_DATABASE
import com.example.mobilechallengeuala.model.database.room.CitiesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class TestCitiesDatabaseModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context, CitiesDataBase::class.java
        ).allowMainThreadQueries()
            .build()

}