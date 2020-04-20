package com.netflixremake.ca.service

import com.netflixremake.ca.model.CategoryResponse
import com.netflixremake.ca.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MoviesInterface {
    @GET("movies.json")
    fun getMovies(): Call<MovieResponse>

    @GET("categories.json")
    fun getCategories(): Call<CategoryResponse>
}