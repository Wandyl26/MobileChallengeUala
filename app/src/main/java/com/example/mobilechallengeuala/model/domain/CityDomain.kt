package com.example.mobilechallengeuala.model.domain

data class CityDomain(
    val id: Int,
    val country: String,
    val name: String,
    val lon: Double,
    val lat: Double,
    var favorite: Boolean
)
