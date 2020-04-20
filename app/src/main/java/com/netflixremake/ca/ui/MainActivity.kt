package com.netflixremake.ca.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jarvis.ca.Mark
import com.netflixremake.ca.R
import com.netflixremake.ca.adapter.CategoryAdapter
import com.netflixremake.ca.controller.MoviesController
import com.netflixremake.ca.model.CategoryResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getMovie()
    }

    private fun getMovie() {
        val call = MoviesController.getCategoriesCall()

        call?.enqueue(object : Callback<CategoryResponse> {
            override fun onFailure(call: Call<CategoryResponse>?, t: Throwable?) {
                Mark.showAlertError(this@MainActivity, "error ${t.toString()}")
            }

            override fun onResponse(
                call: Call<CategoryResponse>?,
                response: Response<CategoryResponse>?
            ) {
                val categoryResponse: CategoryResponse = response?.body()!!

                val layoutManager = LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)
                rvFirst.layoutManager = layoutManager
                rvFirst.adapter = CategoryAdapter(categoryResponse.category)
            }
        })
    }
}