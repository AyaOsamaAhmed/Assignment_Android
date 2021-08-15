package com.aya.assignment_android.ui

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aya.assignment_android.network.RetrofitClient
import com.aya.assignment_android.network.RetrofitClient.Base_context
import com.aya.taskdetails.network.ApiInterface
import com.aya.taskdetails.network.Coroutines
import com.aya.taskdetails.network.repository.GeneralDataRepository
import com.aya.taskdetails.network.responseModel.data.Movie
import kotlinx.coroutines.Job

class ViewModel : ViewModel() {

    var movies_list: MutableLiveData<ArrayList<Movie>>  = MutableLiveData<ArrayList<Movie>>()

    lateinit var getDataJob: Job

    var generalDataRepository: GeneralDataRepository
    var apiClient: ApiInterface

    init {
        apiClient = RetrofitClient.getInstance().api!!
        generalDataRepository = GeneralDataRepository(apiClient,Base_context)
    }


    fun getMovies() {
        getDataJob = Coroutines.ioThenMain({
            try {
                generalDataRepository.getHomeData()
            } catch (e: Exception) {
                (Base_context as Activity?)?.runOnUiThread {
                    Toast.makeText(Base_context,"no internet connection", Toast.LENGTH_SHORT).show()
                }
                Log.e("getMovies", "exception handled" + (e.toString()))

            }
        },
            {
                try {
                    if (it != null)
                        movies_list.value = it as ArrayList<Movie>
                    else {
                        //TODO Display Error
                    }
                } catch (e: Exception) {
                    Log.e("getMovies", "exception handled" + (e.toString()))
                }
            })
    }


}