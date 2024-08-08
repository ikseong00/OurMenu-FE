package com.example.ourmenu.data.menuFolder.response

import com.example.ourmenu.data.menuFolder.data.MenuData
import com.example.ourmenu.data.menuFolder.data.MenuFolderData

data class MenuFolderResponseArray(
    val isSuccess: Boolean,
    val response: ArrayList<MenuFolderData>,
)

data class MenuFolderResponse(
    val isSuccess: Boolean,
    val response: MenuFolderData
)

data class MenuResponseArray(
    val isSuccess: Boolean,
    val response: ArrayList<MenuData>
)

