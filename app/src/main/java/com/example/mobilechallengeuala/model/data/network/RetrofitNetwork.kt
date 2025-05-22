package com.example.mobilechallengeuala.model.data.network

import com.example.mobilechallengeuala.model.data.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitNetwork {


    protected fun setupRetrofit(): CitiesApiClient {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CitiesApiClient::class.java)

    }
}