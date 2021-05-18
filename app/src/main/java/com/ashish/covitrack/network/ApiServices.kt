package com.ashish.covitrack.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServices {
    val virusInstance:CovidApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
        virusInstance = retrofit.create(CovidApi::class.java)
    }
}