package com.avatar22.dailywallpaper.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.avatar22.dailywallpaper.R
import com.avatar22.dailywallpaper.adapter.CategoryAdapter
import com.avatar22.dailywallpaper.adapter.DailyAdapter
import com.avatar22.dailywallpaper.model.Category
import com.avatar22.dailywallpaper.model.Wallpaper
import com.avatar22.dailywallpaper.service.ApiService
import com.avatar22.dailywallpaper.service.ServiceBuilder
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import spencerstudios.com.bungeelib.Bungee

class MainActivity : AppCompatActivity() {

    var runnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        swipe.setOnRefreshListener {

            Handler().postDelayed(Runnable {
                swipe.isRefreshing = false
                loadCategories()
                loadAllWallpapers()


            }, 3000)
        }


        YoYo.with(Techniques.FlipInY)
            .duration(1000)
            .playOn(findViewById(R.id.tv_daily));

        YoYo.with(Techniques.FlipInY)
            .duration(1000)
            .playOn(findViewById(R.id.tv_category));

        tv_daily.setOnClickListener(View.OnClickListener {
            YoYo.with(Techniques.Shake)
                .duration(700)
                .playOn(findViewById(R.id.tv_daily));
            rv_daily.startLayoutAnimation()

        })
        tv_category.setOnClickListener(View.OnClickListener {
            YoYo.with(Techniques.Shake)
                .duration(700)
                .playOn(findViewById(R.id.tv_category));
            rv_category.startLayoutAnimation()
        })


        search_bar.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            this.startActivity(intent)
            Bungee.zoom(this)
        })

        rv_daily.layoutManager = GridLayoutManager(this,1, GridLayoutManager.HORIZONTAL, false)
        rv_category.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        



        loadCategories()
        loadAllWallpapers()


    }

    private fun loadAllWallpapers() {

        var wallpaperList = ArrayList<Wallpaper>()
        val wallpaperService = ServiceBuilder.buildService(ApiService::class.java)


        val requestCall = wallpaperService.getAllWallpaperList()

        requestCall.enqueue(object : retrofit2.Callback<Wallpaper> {
            override fun onResponse(
                call: Call<Wallpaper>,
                response: Response<Wallpaper>
            ) {
                if (response.isSuccessful) {
                    wallpaperList = response.body()!!.getAllWallpapers() as ArrayList<Wallpaper>

                    val adapter_daily = DailyAdapter(wallpaperList)

                    rv_daily.adapter = adapter_daily

                    anim_loading_daily.setVisibility(View.GONE)
                    rv_daily.setVisibility(View.VISIBLE)

                    rv_daily.startLayoutAnimation()

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

    private fun loadCategories() {

        var categoryList = ArrayList<Category>()
        val categoryService = ServiceBuilder.buildService(ApiService::class.java)


        val requestCall = categoryService.getCategoryList()

        requestCall.enqueue(object : retrofit2.Callback<Category> {
            override fun onResponse(
                call: Call<Category>,
                response: Response<Category>
            ) {
                if (response.isSuccessful) {
                    categoryList = response.body()!!.getAllCategories() as ArrayList<Category>

                    val adapter_category = CategoryAdapter(categoryList)

                    rv_category.adapter = adapter_category

                    anim_loading_category.setVisibility(View.GONE)
                    rv_category.setVisibility(View.VISIBLE)

                    rv_category.startLayoutAnimation()

                } else {
                }
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
            }

        })
    }



}