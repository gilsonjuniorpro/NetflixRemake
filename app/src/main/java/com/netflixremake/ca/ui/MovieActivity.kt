package com.netflixremake.ca.ui

import android.content.Context
import android.content.Intent
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import coil.Coil
import coil.api.get
import com.netflixremake.ca.R
import com.netflixremake.ca.adapter.SimilarAdapter
import com.netflixremake.ca.model.Movie
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.coroutines.*

class MovieActivity : AppCompatActivity() {

    var movies: MutableList<Movie> = emptyList<Movie>().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setSupportActionBar(toolBar)

        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            supportActionBar?.title = ""
        }

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)

        var drawable: LayerDrawable = ContextCompat.getDrawable(baseContext, R.drawable.shadows) as LayerDrawable
        lifecycleScope.launch { // runs on Main by default
            var movieCover = withContext(Dispatchers.IO) {
                Coil.get(movie.posterUrl!!)
            }
            drawable.setDrawableByLayerId(R.id.cover_drawable, movieCover)
            ivCover.setImageDrawable(drawable)
        }

        tvMovieName.text = movie.title
        tvDescription.text = movie.description
        tvCast.text = getString(R.string.cast, movie.cast)

        for (i in 1..30) {
            var movie = movies.run {
                Movie(
                    movie.title,
                    movie.description,
                    movie.cast,
                    movie.coverUrl,
                    movie.posterUrl
                )
            }
            movies.add(movie)
        }

        recyclerSimilar.adapter = SimilarAdapter(movies)
        recyclerSimilar.layoutManager = GridLayoutManager(this, 3)
    }

    companion object{
        private const val EXTRA_MOVIE = "movie"

        fun open(context: Context, movie: Movie){
            val intent = Intent(context, MovieActivity::class.java)
            intent.putExtra(EXTRA_MOVIE, movie)
            context.startActivity(intent)
        }
    }
}
