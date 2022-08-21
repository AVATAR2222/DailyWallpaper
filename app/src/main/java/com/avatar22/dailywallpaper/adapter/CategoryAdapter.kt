package com.avatar22.dailywallpaper.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.avatar22.dailywallpaper.R
import com.avatar22.dailywallpaper.activity.CategoryActivity
import com.avatar22.dailywallpaper.model.Category
import com.squareup.picasso.Picasso
import spencerstudios.com.bungeelib.Bungee

class CategoryAdapter(private val categoryList: ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_category, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Category= categoryList[position]

        Picasso.get()
            .load(Category.photo)
            .placeholder(R.color.colorOnPrimary)
            .error(R.color.colorOnPrimary)
            .into(holder.img_category)

        holder.category_name.text = Category.name

        holder.img_category.setOnClickListener(View.OnClickListener {
            val context=holder.img_category.context
            val intent = Intent(context,CategoryActivity::class.java)
            intent.putExtra("category_name",Category.name)
            intent.putExtra("category_id",Category.id)
            context.startActivity(intent)
            Bungee.diagonal(context)

        })
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val img_category: ImageView = itemView.findViewById(R.id.img_category)
        val category_name: TextView = itemView.findViewById(R.id.tv_category_name)
    }
}