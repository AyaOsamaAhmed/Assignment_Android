package com.aya.taskdetails.network.repository

import android.content.Context
import com.aya.taskdetails.network.ApiException
import com.aya.taskdetails.network.ApiInterface
import com.aya.taskdetails.network.SafeAmiRequestLoadingDialogue

class GeneralDataRepository(
    private val api: ApiInterface,
    private val context: Context
) : SafeAmiRequestLoadingDialogue(context) {


    @Throws(ApiException::class)
    suspend fun getHomeData() =
        apiRequest { api.HomeData() }



}