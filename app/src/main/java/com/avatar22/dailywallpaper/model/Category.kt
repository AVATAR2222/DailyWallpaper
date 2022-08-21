package com.avatar22.dailywallpaper.model

import com.google.gson.annotations.SerializedName


data class Category (


    val name:String,
    val photo:String,
    val id:Int



){
    @SerializedName("data")
    val allCategory: List<Category>? = null

    fun getAllCategories(): List<Category>? {
        return allCategory
    }

}
