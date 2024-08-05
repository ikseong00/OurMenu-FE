package com.example.ourmenu.data

data class PlaceInfoResponse(
    val isSuccess: Boolean,
    val response: ArrayList<PlaceInfoData2>,
)

data class PlaceInfoData2(
    val name: String,
    val address: String,
    val type: String,
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

data class PlaceInfoData(
    val placeName: String,
    val type: String,
    val distance: String,
    val recentSearch: Boolean = false,
    val address: String,
    val time: String,
    val imgs: ArrayList<Int>,
    val menus: ArrayList<PlaceMenuData>,
    val mapx: String,
    val mapy: String,
)

data class PlaceMenuData(
    val menuName: String,
    val price: String, // 아니면 int?
)
