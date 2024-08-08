package com.example.ourmenu.retrofit.service

import com.example.ourmenu.data.menuFolder.response.MenuResponseArray
import retrofit2.Call
import retrofit2.http.GET

interface MenuService {
    @GET("menu")
    fun getMenus(): Call<MenuResponseArray>
}
