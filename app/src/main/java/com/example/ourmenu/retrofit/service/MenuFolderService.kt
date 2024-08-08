package com.example.ourmenu.retrofit.service

import com.example.ourmenu.data.menuFolder.request.MenuFolderRequest
import com.example.ourmenu.data.menuFolder.response.MenuFolderResponse
import com.example.ourmenu.data.menuFolder.response.PostMenuFolderResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MenuFolderService {
    @GET("menuFolder")
    fun getMenuFolders(): Call<MenuFolderResponse>

    @POST("menuFolder")
    fun postMenuFolder(
        @Body menuFolderRequest: MenuFolderRequest
    ): Call<PostMenuFolderResponse>
}
