package com.avatar22.dailywallpaper.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.avatar22.dailywallpaper.R
import com.avatar22.dailywallpaper.adapter.WallpaperAdapter
import com.avatar22.dailywallpaper.custom.GridRecyclerView
import com.avatar22.dailywallpaper.model.Wallpaper
import com.avatar22.dailywallpaper.service.ApiService
import com.avatar22.dailywallpaper.service.ServiceBuilder
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Response
import spencerstudios.com.bungeelib.Bungee

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        img_back.setOnClickListener(View.OnClickListener { onBackPressed() })

        rv_wallpaper.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)

        btn_search.setOnClickListener(View.OnClickListener {
            anim_searching.setVisibility(View.VISIBLE)
            anim_searching.playAnimation()
            anim_not_found.setVisibility(View.GONE)
            rv_wallpaper.setVisibility(View.GONE)
            loadWallpapers()
        })


    }

    private fun loadWallpapers() {


        val searchText = et_search.text.toString()


        var wallpaperList = ArrayList<Wallpaper>()
        val wallpaperService = ServiceBuilder.buildService(ApiService::class.java)

        val rv_wallpaper = findViewById<RecyclerView>(R.id.rv_wallpaper)

        val requestCall = wallpaperService.getSearchList(searchText)

        requestCall.enqueue(object : retrofit2.Callback<Wallpaper> {
            override fun onResponse(
                call: Call<Wallpaper>,
                response: Response<Wallpaper>
            ) {
                var res = response;

                if (res.isSuccessful) {


                    wallpaperList = res.body()!!.getAllWallpapers() as ArrayList<Wallpaper>

                    Log.i("dast1", res.body().toString())
                    Log.i("dast2", wallpaperList.toString())
                    if (!wallpaperList.isEmpty()) {

                        anim_searching.setVisibility(View.GONE)
                        rv_wallpaper.setVisibility(View.VISIBLE)
                        val adapter_wallpaper = WallpaperAdapter(wallpaperList)
                        rv_wallpaper.adapter = adapter_wallpaper

                    } else {
                        anim_searching.setVisibility(View.GONE)
                        anim_not_found.setVisibility(View.VISIBLE)
                        anim_not_found.playAnimation()
                    }




                    Log.i("babe", "onResponse:true ")

                } else {
                    Log.i("babe", "onResponse: else")
                }
            }

            override fun onFailure(call: Call<Wallpaper>, t: Throwable) {
                Log.i("babe", "onFailure: ")
            }

        })
    }


    override fun onBackPressed() {
        super.onBackPressed()
        Bungee.zoom(this)
    }
}