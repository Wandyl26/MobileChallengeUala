package com.example.mobilechallengeuala.model.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobilechallengeuala.model.database.CityEntity
import com.example.mobilechallengeuala.model.database.room.daos.CitiesDao

@Database(entities = [CityEntity::class], version = 1)
abstract class CitiesDataBase: RoomDatabase() {
    abstract fun citiesDao(): CitiesDao

}