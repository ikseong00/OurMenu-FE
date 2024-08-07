package com.example.ourmenu.retrofit.service

import com.example.ourmenu.data.place.PlaceInfoResponse
import com.example.ourmenu.data.place.PlaceSearchHistoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PlaceService {
    @GET("place/search")
    fun getPlaceInfo(
        @Header("Authorization") token: String,
        @Query("title") title: String,
    ): Call<PlaceInfoResponse>

    @GET("place/search-history")
    fun getPlaceSearchHistory(
        @Header("Authorization") token: String,
    ): Call<PlaceSearchHistoryResponse>

//    @GET("place/{id}")
//    fun getPlaceInfoDetail(@Query("id") id:String): Call
}
