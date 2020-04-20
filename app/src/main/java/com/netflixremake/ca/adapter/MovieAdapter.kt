package com.netflixremake.ca.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.netflixremake.ca.R
import com.netflixremake.ca.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*


class MovieAdapter(
    private val items: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(layout)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie:Movie = items[position]

        if (movie.coverUrl != null) {
            holder.ivCover.load(movie.coverUrl) {
                crossfade(true)
                crossfade(500)
                placeholder(R.drawable.image_not_found)
            }
        } else {
            holder.ivCover.setImageResource(R.drawable.image_not_found)
        }
        holder.itemView.setOnClickListener { onItemClick(movie) }
    }

    class MovieViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val ivCover: ImageView = rootView.ivCover
    }
}