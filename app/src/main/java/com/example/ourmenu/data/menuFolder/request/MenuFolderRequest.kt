package com.example.ourmenu.data.menuFolder.request

import com.google.gson.annotations.SerializedName

data class MenuFolderRequest(
    val menuFolderImgUrl: String,
    val menuFolderTitle: String,
    val menuFolderIcon: String
)
