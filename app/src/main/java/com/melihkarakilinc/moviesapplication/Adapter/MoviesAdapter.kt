package com.melihkarakilinc.moviesapplication.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melihkarakilinc.moviesapplication.Result
import com.melihkarakilinc.moviesapplication.Utils.ItemListener
import com.melihkarakilinc.moviesapplication.Utils.Util
import com.melihkarakilinc.moviesapplication.databinding.ItemLayoutBinding

class MoviesAdapter() : RecyclerView.Adapter<ViewHolder>() {

    private var movies = mutableListOf<Result>()
    lateinit var context: Context
    lateinit var itemListener: ItemListener

    @SuppressLint("NotifyDataSetChanged")
    fun movieList(movie: List<Result>, itemListener: ItemListener) {
        this.movies = movie.toMutableList()
        this.itemListener = itemListener
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.txtMovieTitle.text = movie.title
        val util=Util()
        util.imageLoader(movie.poster_path.toString(),holder.binding.imgPoster)
        holder.binding.cardView.setOnClickListener {
            itemListener.OnItemSelect(movie)
        }
    }

    override fun getItemCount(): Int = movies.size
}

class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)