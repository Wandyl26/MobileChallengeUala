package com.example.mobilechallengeuala.model.data

import com.google.gson.annotations.SerializedName

data class LocationsModel(
  @SerializedName("country") val country: String,
  @SerializedName("name") val name: String,
  @SerializedName("_id") val _id: Int,
  @SerializedName("coord") val coord: CoordinatesModel
)