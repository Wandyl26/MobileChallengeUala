package com.example.mobilechallengeuala.model.domain

import com.example.mobilechallengeuala.model.data.CityModel
import com.example.mobilechallengeuala.model.data.CoordinatesModel
import com.example.mobilechallengeuala.model.database.CityEntity

class QueriesCitiesDataBaseDomain() {

    suspend fun insertCities(cityModel: List<CityModel>){
        CitiesDataBaseDomain.citiesDao.insertIfNotExist(convertListCitiesModel(cityModel))
    }

    suspend fun getSearchCities(search:String): List<CityDomain>{
        return convertListCitiesEntity(CitiesDataBaseDomain.citiesDao.getSearchCities(search))
    }
    suspend fun getFavoriteCities(): List<CityDomain>{
        return convertListCitiesEntity(CitiesDataBaseDomain.citiesDao.getFavoriteCities())
    }
    suspend fun updateCity(cityDomain: CityDomain){
        val cityEntity = convertCitiesDomain(cityDomain)
        CitiesDataBaseDomain.citiesDao.updateCity(cityEntity)
    }
}

fun convertCitiesModel(cityModel: CityModel): CityEntity {
    val cityEntity = CityEntity(cityModel._id, cityModel.country, cityModel.name, cityModel.coord.lat, cityModel.coord.lon)
    return cityEntity
}

fun convertListCitiesModel(cityModel: List<CityModel>): MutableList<CityEntity> {
    val cityEntityList = mutableListOf<CityEntity>()
    for (cityModel in cityModel){
        cityEntityList.add(convertCitiesModel(cityModel))
    }
    return cityEntityList
}

fun convertCitiesEntity(cityEntity: CityEntity): CityDomain {
    val cityDomain=CityDomain(cityEntity.id, cityEntity.country, cityEntity.name, cityEntity.lon, cityEntity.lat)
    return cityDomain
}
fun convertListCitiesEntity(cityEntity: List<CityEntity>): MutableList<CityDomain> {
    val cityDomainList = mutableListOf<CityDomain>()
    for (cityEntity in cityEntity){
        cityDomainList.add(convertCitiesEntity(cityEntity))
    }
    return cityDomainList
}
fun convertCitiesDomain(cityDomain: CityDomain): CityEntity {
    val cityEntity = CityEntity(cityDomain.id, cityDomain.country, cityDomain.name, cityDomain.lat, cityDomain.lon)
    return cityEntity
}