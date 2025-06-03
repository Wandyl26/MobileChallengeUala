package com.example.mobilechallengeuala

import com.example.mobilechallengeuala.model.data.CityModel
import com.example.mobilechallengeuala.model.data.CoordinatesModel
import com.example.mobilechallengeuala.model.database.CityEntity
import com.example.mobilechallengeuala.model.domain.CityDomain
import com.example.mobilechallengeuala.model.domain.convertCitiesDomain
import com.example.mobilechallengeuala.model.domain.convertListCitiesEntity
import com.example.mobilechallengeuala.model.domain.convertListCitiesModel
import org.junit.Assert.assertEquals
import org.junit.Test

class QueriesCitiesDataBaseDomainTest {

    @Test
    fun convertListCitiesModelToCitiesEntity(){
        //Arrange

        val citiesModel = mutableListOf<CityModel>()
        val citiesEntity : MutableList<CityEntity>

        //Act
        citiesModel.add(CityModel("CO","Berlín",3688889, CoordinatesModel(-75.547958, 6.29238)))
        citiesModel.add(CityModel("CO","Yumbo",3665657, CoordinatesModel(-76.491463, 3.58234)))
        citiesModel.add(CityModel("CO","Itagui",3680450, CoordinatesModel(-75.611389, 6.17194)))
        citiesModel.add(CityModel("CO","Buga",3688256, CoordinatesModel(-76.297829, 3.90089)))
        citiesModel.add(CityModel("CO","Girardot",3682028, CoordinatesModel(-74.80468, 4.29866)))

        citiesEntity=convertListCitiesModel(citiesModel)
        //Assert

        assert(citiesEntity.size == citiesModel.size)
        for (i in citiesModel.indices){
            assertEquals(false,citiesEntity[i].favorite)
            assertEquals(citiesModel[i].country,citiesEntity[i].country)
            assertEquals(citiesModel[i].name,citiesEntity[i].name)
            assertEquals(citiesModel[i]._id,citiesEntity[i].id)
            assertEquals(citiesModel[i].coord.lon,citiesEntity[i].lon,0.0)
            assertEquals(citiesModel[i].coord.lat,citiesEntity[i].lat,0.0)
        }

    }

    @Test
    fun convertListCitiesEntityToCitiesModel(){
        //Arrange

        val citiesEntity = mutableListOf<CityEntity>()
        val citiesDomain : MutableList<CityDomain>

        //Act
        citiesEntity.add(CityEntity(3688889, "CO","Berlín"  ,-75.547958, 6.29238))
        citiesEntity.add(CityEntity(3665657, "CO","Yumbo"   ,-76.491463, 3.58234,true))
        citiesEntity.add(CityEntity(3680450, "CO","Itagui"  ,-75.611389, 6.17194))
        citiesEntity.add(CityEntity(3688256, "CO","Buga"    ,-76.297829, 3.90089, true))
        citiesEntity.add(CityEntity(3682028, "CO","Girardot",-74.80468, 4.29866))

        citiesDomain= convertListCitiesEntity(citiesEntity)
        //Assert

        assert(citiesEntity.size == citiesDomain.size)
        for (i in citiesDomain.indices){
            assertEquals(citiesDomain[i].favorite, citiesEntity[i].favorite)
            assertEquals(citiesDomain[i].country, citiesEntity[i].country)
            assertEquals(citiesDomain[i].name, citiesEntity[i].name)
            assertEquals(citiesDomain[i].id, citiesEntity[i].id)
            assertEquals(citiesDomain[i].lon, citiesEntity[i].lon,0.0)
            assertEquals(citiesDomain[i].lat, citiesEntity[i].lat,0.0)
        }


    }

    @Test
    fun convertCitiesDomainToCitiesEntity(){
        //Arrange
        val cityDomain = CityDomain(3688256, "CO","Buga"    ,-76.297829, 3.90089,true)
        var cityEntity : CityEntity

        //Act
        cityEntity=convertCitiesDomain(cityDomain)

        //Assert

        assertEquals(cityDomain.favorite, cityEntity.favorite)
        assertEquals(cityDomain.country, cityEntity.country)
        assertEquals(cityDomain.name, cityEntity.name)
        assertEquals(cityDomain.id, cityEntity.id)
        assertEquals(cityDomain.lon, cityEntity.lon,0.0)
        assertEquals(cityDomain.lat, cityEntity.lat,0.0)

    }

}