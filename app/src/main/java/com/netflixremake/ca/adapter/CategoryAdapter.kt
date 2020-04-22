package com.netflixremake.ca.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.netflixremake.ca.R
import com.netflixremake.ca.model.Category
import com.netflixremake.ca.model.Movie
import com.netflixremake.ca.ui.MovieActivity
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter (
    private val items: List<Category>
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        layoutManager = LinearLayoutManager(parent.context, RecyclerView.HORIZONTAL, false)
        context = parent.context

        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(layout)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category: Category = items[position]
        holder.tvCategory.text = category.name
        holder.recyclerMovie.layoutManager = layoutManager
        holder.recyclerMovie.adapter = MovieAdapter(category.movies, this::openDetail)
    }

    private fun openDetail(movie: Movie) {
        MovieActivity.open(context, movie)
    }

    class CategoryViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val tvCategory: TextView = rootView.tvCategory
        val recyclerMovie: RecyclerView = rootView.recyclerMovie
    }
}