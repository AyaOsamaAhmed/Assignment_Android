package com.aya.taskdetails.network

import com.aya.taskdetails.network.responseModel.HomeResponse
import retrofit2.Response
import retrofit2.http.*


interface ApiInterface {

    @GET("movies")
    suspend fun HomeData(): Response<HomeResponse>


}