package com.example.ourmenu.data

data class MenuFolderResponse(
    val isSuccess: Boolean,
    val response: List<MenuFolder>,
)

data class MenuFolder(
    val title: String,
    val menuCount: Int,
    val imgUrl: String,
    val iconType: Int,
)
