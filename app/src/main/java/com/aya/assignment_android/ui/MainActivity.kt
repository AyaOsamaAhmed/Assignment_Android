package com.aya.assignment_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.aya.assignment_android.R
import com.aya.assignment_android.databinding.ActivityMainBinding
import com.aya.assignment_android.network.RetrofitClient.Base_context
import com.aya.taskdetails.adapter.MovieAdapter

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var viewModel :ViewModel
    lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // set activity
        Base_context = this
        //call Dagger
    //    val app : ComponentMovie = DaggerComponentMovie.creat()
    //    (applicationContext as ComponentMovie).inject(this)


        //call view model
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)
        //get movie from api
        viewModel.getMovies()
        // observer from view model
        viewModel.movies_list.observe(this, Observer {
           // Toast.makeText(this,it[0].id,Toast.LENGTH_LONG).show()
            adapter = MovieAdapter(this,it)
            val gridLayoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
            binding.movieRecyclerview.layoutManager = gridLayoutManager
            binding.movieRecyclerview.setHasFixedSize(true)
            binding.movieRecyclerview.adapter = adapter

        })

    }
}