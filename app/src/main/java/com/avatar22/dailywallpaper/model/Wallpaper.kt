package com.avatar22.dailywallpaper.model

import com.google.gson.annotations.SerializedName


data class Wallpaper(

    val name:String,
    val photo:String,
    val category_id:Int,
    val created_at:String
){

    @SerializedName("data")
    val allWallpaper: List<Wallpaper>? = null


    fun getAllWallpapers(): List<Wallpaper>? {
        return allWallpaper
    }
}