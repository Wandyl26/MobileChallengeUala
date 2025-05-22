package com.example.mobilechallengeuala.model.domain

import android.content.Context
import androidx.room.Room
import com.example.mobilechallengeuala.model.database.room.CitiesDataBase
import com.example.mobilechallengeuala.model.database.room.daos.CitiesDao

object CitiesDataBaseDomain {

    private var INSTANCE: CitiesDataBase? = null
    val citiesDao: CitiesDao
        get() = INSTANCE!!.citiesDao()

    fun getDatabase(context: Context): CitiesDataBase {
        if (INSTANCE == null) {
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    CitiesDataBase::class.java,
                    "cities-db"
                ).build()
            }
        }
        return INSTANCE!!
    }
}