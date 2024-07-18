package com.odearmas.buscapelis.data

import com.google.gson.annotations.SerializedName
data class PeliThumb(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Poster") val imageURL: String,
    @SerializedName("imdbID") val imdbID: String
)

