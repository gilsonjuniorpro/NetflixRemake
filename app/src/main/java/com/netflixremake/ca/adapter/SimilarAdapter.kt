package com.netflixremake.ca.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.netflixremake.ca.R
import com.netflixremake.ca.model.Movie
import kotlinx.android.synthetic.main.item_similar.view.*


class SimilarAdapter(
    private val items: List<Movie>
) : RecyclerView.Adapter<SimilarAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_similar, parent, false)
        return MovieViewHolder(layout)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie:Movie = items[position]

        if (movie.coverUrl != null) {
            holder.ivCoverSimilar.load(movie.coverUrl) {
                crossfade(true)
                crossfade(500)
                placeholder(R.drawable.image_not_found)
            }
        } else {
            holder.ivCoverSimilar.setImageResource(R.drawable.image_not_found)
        }
        //holder.itemView.setOnClickListener { onItemClick(story) }
    }

    class MovieViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val ivCoverSimilar: ImageView = rootView.ivCoverSimilar
    }
}