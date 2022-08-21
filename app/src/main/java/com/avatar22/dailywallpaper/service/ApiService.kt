package com.avatar22.dailywallpaper.service

import com.avatar22.dailywallpaper.model.Category
import com.avatar22.dailywallpaper.model.Wallpaper
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {


    @GET("categories")
    fun getCategoryList(): Call<Category>

    @GET("wallpapers")
    fun getAllWallpaperList(): Call<Wallpaper>

    @GET("categories/{category_id}")
    fun getWallpaperList(@Path("category_id") category_id: Int): Call<Wallpaper>

    @GET("wallpapers/")
    fun getSearchList(@Query("search") search: String): Call<Wallpaper>

}

//fun groupList(@Path("id") groupId: Int): Call<List<User?>?>?