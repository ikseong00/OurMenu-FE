package com.example.ourmenu.data

data class AddMenuSearchResultData(
    val placeName: String,
    val address: String,
    val type: String,
    val distance: String,
    val recentSearch: Boolean = false,
)
