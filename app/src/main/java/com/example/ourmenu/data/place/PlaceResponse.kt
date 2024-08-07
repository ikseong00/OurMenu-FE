package com.example.ourmenu.data.place

import com.example.ourmenu.data.ErrorResponse

// place/search
data class PlaceInfoResponse(
    val isSuccess: Boolean,
    val response: ArrayList<PlaceInfoData2>,
    val errorResponse: ErrorResponse?,
)

// place/search-history
data class PlaceSearchHistoryResponse(
    val isSuccess: Boolean,
    val response: ArrayList<PlaceSearchHistoryData>,
    val errorResponse: ErrorResponse?,
)
