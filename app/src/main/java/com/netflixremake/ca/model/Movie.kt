package com.netflixremake.ca.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Movie (
    val title: String?,
    val description: String?,
    val cast: String?,
    val coverUrl: String?
): Parcelable