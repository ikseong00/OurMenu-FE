package com.example.ourmenu.retrofit.service

import com.example.ourmenu.data.BaseResponse
import com.example.ourmenu.data.menuFolder.request.MenuFolderRequest
import com.example.ourmenu.data.menuFolder.response.MenuFolderResponse
import com.example.ourmenu.data.menuFolder.response.MenuFolderArrayResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MenuFolderService {
    @GET("menuFolder")
    fun getMenuFolders(): Call<MenuFolderArrayResponse>

    @POST("menuFolder")
    fun postMenuFolder(
        @Body menuFolderRequest: MenuFolderRequest
    ): Call<MenuFolderResponse>

    @PATCH("menuFolder/{menuFolderId}")
    fun patchMenuFolder(
        @Path("menuFolderId") menuFolderId: Int,
        @Body menuFolderRequest: MenuFolderRequest
    ): Call<MenuFolderResponse>

    @DELETE("menuFolder/{menuFolderId}")
    fun deleteMenuFolder(
        @Path("menuFolderId") menuFolderId: Int
    ):Call<Boolean>

    @PATCH("/menuFolder/priority/{menuFolderId}")
    fun patchPriority(
        @Path("menuFolderId") menuFolderId: Int,
        @Query("newPriority") newPriority: Int
    ):Call<BaseResponse>
}
