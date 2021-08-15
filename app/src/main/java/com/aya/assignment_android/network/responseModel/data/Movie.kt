package com.aya.taskdetails.network.responseModel.data


import androidx.annotation.Keep

@Keep
data class Movie(
    var id: String = "", // Live Code Stream
    var type: String = "", // type of url
    var url: String = "", // https://nagwa.free.beeceptor.com/
    var name: String = "" // title url
)