package com.aya.taskdetails.network

import com.aya.taskdetails.network.responseModel.HomeResponse
import com.aya.taskdetails.network.responseModel.data.Movie
import dagger.Component
import retrofit2.Response
import retrofit2.http.*

@Component
interface ApiInterface {

    @GET("movies")
    suspend fun HomeData(): Response<ArrayList<Movie>>


}