package com.example.ourmenu.data.menuFolder.response

import com.example.ourmenu.data.menuFolder.data.MenuFolderData

data class MenuFolderResponse(
    val isSuccess: Boolean,
    val response: ArrayList<MenuFolderData>,
)

