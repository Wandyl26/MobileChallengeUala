package com.example.mobilechallengeuala.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    @ColumnInfo("country") val country: String = "",
    @ColumnInfo("name") val name: String = "",
    @ColumnInfo("lon") val lon: Double = 0.0,
    @ColumnInfo("lat") val  lat: Double = 0.0,
    @ColumnInfo("favorite") var favorite: Boolean = false
)
