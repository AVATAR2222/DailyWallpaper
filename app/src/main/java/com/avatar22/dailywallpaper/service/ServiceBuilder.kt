package com.avatar22.dailywallpaper.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private const val Url="http://192.168.1.5/Laravel-Wallpaper-Api/public/api/"

    // Create OkHttp Client
    private val okHttp = OkHttpClient.Builder()

    // Create Retrofit Builder
    private val builder = Retrofit.Builder().baseUrl(Url).addConverterFactory(GsonConverterFactory.create()).client(
        okHttp.build())

    // Create Retrofit Instance
    private val retrofit= builder.build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }



//    //download image
//    var BASE_URL:String=""
//    val getClient: ApiService
//        get() {
//
//            val interceptor = HttpLoggingInterceptor()
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//
//            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
//
//            val retrofit = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(client)
//                .build()
//
//            return retrofit.create(ApiService::class.java)
//        }


}