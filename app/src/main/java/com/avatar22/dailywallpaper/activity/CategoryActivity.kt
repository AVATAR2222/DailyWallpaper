package com.avatar22.dailywallpaper.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.avatar22.dailywallpaper.R
import com.avatar22.dailywallpaper.adapter.WallpaperAdapter
import com.avatar22.dailywallpaper.model.Wallpaper
import com.avatar22.dailywallpaper.service.ApiService
import com.avatar22.dailywallpaper.service.ServiceBuilder
import kotlinx.android.synthetic.main.activity_category.*
import retrofit2.Call
import retrofit2.Response
import spencerstudios.com.bungeelib.Bungee

open class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)




        val category_name = intent.getSerializableExtra("category_name").toString()

        tv_category_name.setText(category_name)


        rv_wallpaper.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)


        loadWallpapers()
        img_back.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
    }


    private fun loadWallpapers() {

        val category_id = intent.getSerializableExtra("category_id")

        var wallpaperList = ArrayList<Wallpaper>()
        val wallpaperService = ServiceBuilder.buildService(ApiService::class.java)


        val requestCall = wallpaperService.getWallpaperList(category_id = category_id as Int)

        requestCall.enqueue(object : retrofit2.Callback<Wallpaper> {
            override fun onResponse(
                call: Call<Wallpaper>,
                response: Response<Wallpaper>
            ) {
                if (response.isSuccessful) {
                    wallpaperList = response.body()!!.getAllWallpapers() as ArrayList<Wallpaper>

                    val adapter_wallpaper = WallpaperAdapter(wallpaperList)

                    rv_wallpaper.adapter = adapter_wallpaper
                    rv_wallpaper.startLayoutAnimation()

                    Log.i("waaaaaaaaaaat", wallpaperList.toString())
                } else {
                    Log.i("waaaaaaaaaant", response.toString())
                }
            }

            override fun onFailure(call: Call<Wallpaper>, t: Throwable) {
                Log.i("waaaat", t.toString())
            }

        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Bungee.zoom(this)
    }



}