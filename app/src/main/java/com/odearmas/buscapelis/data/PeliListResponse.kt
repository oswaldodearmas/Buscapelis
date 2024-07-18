package com.odearmas.buscapelis.data

import com.google.gson.annotations.SerializedName

data class PeliListResponse(
    @SerializedName("Search") val results: List<PeliThumb>
)