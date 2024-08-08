package com.example.ourmenu.retrofit.service

import com.example.ourmenu.data.menu.response.MenuArrayResponse
import retrofit2.Call
import retrofit2.http.GET

interface MenuService {
    @GET("menu")
    fun getMenus(): Call<MenuArrayResponse>
}
