package com.aya.taskdetails.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.aya.assignment_android.R
import com.aya.assignment_android.network.RetrofitClient.Base_context
import com.aya.taskdetails.network.responseModel.data.Movie
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


class MovieAdapter(val context: Context, movie: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>()  {
    var movie_list: List<Movie> = ArrayList()
    var mContext: Context

    init {
        this.mContext = context
        this.movie_list = movie
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movie_item = movie_list[position]
        holder.bind(holder.movie_item!!)

    }


    override fun getItemCount(): Int {
        return movie_list.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener  {


        var movie_item: Movie? = null
        var title_movie: TextView?
        var type_movie: TextView?
        var videoVM : VideoView
        var pdfVM: WebView
        var url :String =""

         init {
            title_movie = view.findViewById(R.id.title_url)
            type_movie = view.findViewById(R.id.type_movie)
             videoVM = view.findViewById(R.id.videoVM)
             pdfVM = view.findViewById(R.id.pdfVM)
            view.setOnClickListener(this)
        }

        fun bind(movieItem: Movie?) {
            title_movie?.text = movieItem!!.name
            type_movie?.text = movieItem!!.type
            url = movieItem.url
            if (type_movie!!.text.equals("VIDEO")) {
                if (!movieItem.url.equals("")) {
                    // load url video in design
                    videoVM.visibility = View.VISIBLE
                    pdfVM.visibility = View.GONE

                     videoVM!!.setMediaController(MediaController(Base_context));
                     videoVM!!.setVideoPath(url)
                     videoVM!!.start()
                }
            }else{
                pdfVM.visibility = View.VISIBLE
                videoVM.visibility = View.GONE
                pdfVM.webViewClient = WebViewClient()
                pdfVM.settings.setSupportZoom(true)
                pdfVM.settings.javaScriptEnabled = true

                pdfVM.loadUrl("https://docs.google.com/gview?embedded=true&url=$url")
            }
        }

        override fun onClick(view: View) {
            Log.i("movie adapter","clicking.........." + url)
            var bundle = Bundle()
            bundle.putString("title", movie_item!!.name)
            bundle.putString("url", movie_item!!.url)

            // move to fragment details
          //  navController!!.navigate(R.id.action_FragmentList_to_FragmentDetails,bundle)
        }

    }

}