package com.odearmas.buscapelis.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeliAPIService {
    @GET("/?apikey=88bf6f95")
    suspend fun searchPelisByName(@Query("s") query:String): PeliListResponse

    @GET("/?apikey=88bf6f95")
    suspend fun searchPeliById(@Query("i") query:String): PeliResponse

}
