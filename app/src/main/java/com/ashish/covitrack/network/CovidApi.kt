package com.ashish.covitrack.network

import com.ashish.covitrack.models.VirusModel
import retrofit2.Call
import retrofit2.http.GET


//https://corona.lmao.ninja/v2/countries
const val BASE_URL="https://corona.lmao.ninja/v2/"
interface CovidApi {
    @GET("countries")
    fun getData(): Call<MutableList<VirusModel>>
}