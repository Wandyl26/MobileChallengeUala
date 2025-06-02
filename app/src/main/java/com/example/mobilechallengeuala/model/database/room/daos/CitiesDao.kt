package com.example.mobilechallengeuala.model.database.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mobilechallengeuala.model.database.CityEntity

@Dao
abstract class CitiesDao {

    @Query("SELECT * FROM cities WHERE LOWER(name) LIKE :search || '%' ORDER BY name ASC LIMIT 20")
    abstract suspend fun getSearchCities(search: String): List<CityEntity>

    @Query("SELECT * FROM cities WHERE favorite =true ORDER BY name ASC LIMIT 20")
    abstract suspend fun getFavoriteCities(): List<CityEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertIfNotExist(cities: List<CityEntity>)

    @Update
    abstract suspend fun updateCity(cities: CityEntity)

}
