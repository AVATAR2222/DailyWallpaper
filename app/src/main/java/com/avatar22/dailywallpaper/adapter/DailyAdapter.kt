package com.avatar22.dailywallpaper.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.avatar22.dailywallpaper.R
import com.avatar22.dailywallpaper.activity.CategoryActivity
import com.avatar22.dailywallpaper.activity.WallpaperActivity
import com.avatar22.dailywallpaper.model.Wallpaper
import com.squareup.picasso.Picasso
import spencerstudios.com.bungeelib.Bungee

class DailyAdapter(private val wallpaperList: ArrayList<Wallpaper>) : RecyclerView.Adapter<DailyAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_daily, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val Wallpaper = wallpaperList[position]

        Picasso.get()
            .load(Wallpaper.photo)
            .placeholder(R.color.colorOnPrimary)
            .error(R.color.colorOnPrimary)
            .into(holder.img_daily)

        holder.img_daily.setOnClickListener(View.OnClickListener {
            val context=holder.img_daily.context
            val intent = Intent(context, WallpaperActivity::class.java)
            intent.putExtra("wallpaper_photo",Wallpaper.photo)
            context.startActivity(intent)
            Bungee.zoom(context)

        })
    }

    override fun getItemCount(): Int {
        return wallpaperList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val img_daily: ImageView = itemView.findViewById(R.id.img_daily)
    }
}