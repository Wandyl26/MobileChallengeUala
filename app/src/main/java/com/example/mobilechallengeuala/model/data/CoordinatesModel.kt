package com.example.mobilechallengeuala.model.data

import com.google.gson.annotations.SerializedName

data class CoordinatesModel(@SerializedName("lon") val lon: Double,
                            @SerializedName("lat") val  lat: Double)

