package com.example.mobilechallengeuala.model.domain

import com.example.mobilechallengeuala.model.data.CityModel
import com.example.mobilechallengeuala.model.data.CoordinatesModel
import com.example.mobilechallengeuala.model.database.CityEntity

class QueriesCitiesDataBaseDomain() {

    suspend fun insertCities(cityModel: List<CityModel>){
        CitiesDataBaseDomain.citiesDao.insertIfNotExist(convertListCitiesModel(cityModel))
    }
    suspend fun getSearchCities(search:String): List<CityModel>{
        return convertListCitiesEntity(CitiesDataBaseDomain.citiesDao.getSearchCities(search))
    }
    suspend fun getFavoriteCities(): List<CityModel>{
        return convertListCitiesEntity(CitiesDataBaseDomain.citiesDao.getFavoriteCities())
    }
    suspend fun updateCity(cityModel: CityModel, isFavorite: Boolean){
        val cityEntity = convertCitiesModel(cityModel)
        cityEntity.favorite = isFavorite
        CitiesDataBaseDomain.citiesDao.updateCity(cityEntity)
    }
}

fun convertCitiesModel(cityModel: CityModel): CityEntity {
    val cityEntity = CityEntity(cityModel._id, cityModel.country, cityModel.name, cityModel.coord.lat, cityModel.coord.lon)
    return cityEntity
}
fun convertCitiesEntity(cityEntity: CityEntity): CityModel {
    val cityModel=CityModel(cityEntity.country, cityEntity.name, cityEntity.id, CoordinatesModel(cityEntity.lat,cityEntity.lon))
    return cityModel
}

fun convertListCitiesModel(cityModel: List<CityModel>): MutableList<CityEntity> {
    val cityEntityList = mutableListOf<CityEntity>()
    for (cityModel in cityModel){
        cityEntityList.add(convertCitiesModel(cityModel))
    }
    return cityEntityList
}
fun convertListCitiesEntity(cityEntity: List<CityEntity>): MutableList<CityModel> {
    val cityModelList = mutableListOf<CityModel>()
    for (cityEntity in cityEntity){
        cityModelList.add(convertCitiesEntity(cityEntity))
    }
    return cityModelList
}