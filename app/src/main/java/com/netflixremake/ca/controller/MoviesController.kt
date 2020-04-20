package com.netflixremake.ca.controller

import com.netflixremake.ca.model.CategoryResponse
import com.netflixremake.ca.model.MovieResponse
import com.netflixremake.ca.service.MoviesInterface
import com.netflixremake.ca.util.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesController {
    companion object{
        fun getRetrofit(): MoviesInterface? {
            val retrofit = Retrofit.Builder().baseUrl(Constants.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(MoviesInterface::class.java)
        }

        fun getMoviesCall(): Call<MovieResponse>? {
            return getRetrofit()?.getMovies()
        }

        fun getCategoriesCall(): Call<CategoryResponse>? {
            return getRetrofit()?.getCategories()
        }
    }
}