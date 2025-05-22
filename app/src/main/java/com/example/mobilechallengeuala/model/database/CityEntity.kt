package com.example.mobilechallengeuala.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo("country") val country: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("lon") val lon: Double,
    @ColumnInfo("lat") val  lat: Double,
    @ColumnInfo("favorite") var favorite: Boolean=false
)
