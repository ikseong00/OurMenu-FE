package com.example.ourmenu.data.menuFolder.data

import com.google.gson.annotations.SerializedName


// /menuFolder
data class MenuFolderData(
    val menuCount: Int,
    val menuFolderIcon: String,
    val menuFolderImgUrl: String,
    val menuFolderPriority: Int,
    val menuFolderTitle: String
)


data class MenuData(
    val menuId: Int,
    val menuTitle: String,
    val placeTitle: String,
    val placeAddress: String,
    val menuPrice: Int,
    val menuImgUrl: String
)
