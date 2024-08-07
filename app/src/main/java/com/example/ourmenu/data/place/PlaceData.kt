package com.example.ourmenu.data.place

data class PlaceInfoData2(
    val name: String,
    val address: String,
//    val type: String,
    val images: ArrayList<String>,
    val menus: ArrayList<PlaceInfoMenuData>,
    val time: String?,
    val mapx: String,
    val mapy: String,
)

data class PlaceInfoMenuData(
    val name: String,
    val price: String,
)

data class PlaceSearchHistoryData(
    val storeName: String,
    val address: String,
)

data class PlaceSearchData(
    val placeId: String,
    val placeTitle: String,
    val placeAddress: String,
)
