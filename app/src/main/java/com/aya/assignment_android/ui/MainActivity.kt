package com.aya.assignment_android.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aya.assignment_android.R
import com.aya.assignment_android.network.RetrofitClient.Base_context

class MainActivity : AppCompatActivity() {


    lateinit var viewModel :ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Base_context = this

        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)
        viewModel.getMovies()

        viewModel.movies_list.observe(this, Observer {
            Toast.makeText(this,it[0].id,Toast.LENGTH_LONG).show()
        })

    }
}