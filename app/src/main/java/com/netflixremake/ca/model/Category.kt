package com.netflixremake.ca.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Category (
    val name: String?,
    val movies: List<Movie>
): Parcelable