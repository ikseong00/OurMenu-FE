package com.example.ourmenu.service

import com.example.ourmenu.data.MenuFolderResponse
import retrofit2.Call
import retrofit2.http.GET

interface MenuFolderService {
    @GET("menuFolder")
    fun getMenuFolders(): Call<MenuFolderResponse>
}
