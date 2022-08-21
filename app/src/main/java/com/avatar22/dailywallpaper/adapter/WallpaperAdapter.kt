package com.avatar22.dailywallpaper.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.avatar22.dailywallpaper.model.ItemsViewModel
import com.avatar22.dailywallpaper.R
import com.avatar22.dailywallpaper.activity.WallpaperActivity
import com.avatar22.dailywallpaper.model.Wallpaper
import com.squareup.picasso.Picasso
import spencerstudios.com.bungeelib.Bungee

class WallpaperAdapter(private val wallpaperList: ArrayList<Wallpaper>) : RecyclerView.Adapter<WallpaperAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_wallpaper, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val Wallpaper= wallpaperList[position]


        // sets the image to the imageview from our itemHolder class
        Picasso.get()
            .load(Wallpaper.photo)
            .placeholder(R.color.colorOnPrimary)
            .error(R.color.colorOnPrimary)
            .into(holder.img_wallpaper)

        holder.img_wallpaper.setOnClickListener(View.OnClickListener {
            val context=holder.img_wallpaper.context
            val intent = Intent(context, WallpaperActivity::class.java)
            intent.putExtra("wallpaper_photo",Wallpaper.photo)
            context.startActivity(intent)
            Bungee.zoom(context)

        })

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return wallpaperList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val img_wallpaper: ImageView = itemView.findViewById(R.id.img_wallpaper)
    }
}