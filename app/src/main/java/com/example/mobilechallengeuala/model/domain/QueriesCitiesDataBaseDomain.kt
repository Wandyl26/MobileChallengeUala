package com.example.mobilechallengeuala.model.domain

import com.example.mobilechallengeuala.model.data.CityModel
import com.example.mobilechallengeuala.model.database.CityEntity
import com.example.mobilechallengeuala.model.database.room.daos.CitiesDao
import javax.inject.Inject

class QueriesCitiesDataBaseDomain @Inject constructor(
    private val citiesDao: CitiesDao
) {

    suspend fun insertCities(cityModel: List<CityModel>){
        citiesDao.insertIfNotExist(convertListCitiesModel(cityModel))
    }

    suspend fun getSearchCities(search:String): List<CityDomain>{
        return convertListCitiesEntity(citiesDao.getSearchCities(search))
    }
    suspend fun getFavoriteCities(): List<CityDomain>{
        return convertListCitiesEntity(citiesDao.getFavoriteCities())
    }
    suspend fun updateCity(cityDomain: CityDomain){
        val cityEntity = convertCitiesDomain(cityDomain)
        citiesDao.updateCity(cityEntity)
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
    val cityDomain=CityDomain(cityEntity.id, cityEntity.country, cityEntity.name, cityEntity.lon, cityEntity.lat, cityEntity.favorite)
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
    val cityEntity = CityEntity(cityDomain.id, cityDomain.country, cityDomain.name, cityDomain.lat, cityDomain.lon, cityDomain.favorite)
    return cityEntity
}