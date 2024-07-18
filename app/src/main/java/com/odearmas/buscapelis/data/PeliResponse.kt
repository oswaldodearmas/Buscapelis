package com.odearmas.buscapelis.data

import com.google.gson.annotations.SerializedName
data class PeliResponse(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Poster") val imageURL: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Runtime") val runtime: String,
    @SerializedName("Director") val director: String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Country") val country: String,
    @SerializedName("imdbID") val imdbID: String
)

