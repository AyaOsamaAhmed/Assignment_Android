package com.aya.taskdetails.network.responseModel


import androidx.annotation.Keep
import com.aya.taskdetails.network.responseModel.data.Movie

@Keep
data class HomeResponse(
        val movies: ArrayList<Movie>
)