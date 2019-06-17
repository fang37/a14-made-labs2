package com.dicoding.picodiploma.myrecyclerview.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.myrecyclerview.R
import com.dicoding.picodiploma.myrecyclerview.model.Hero
import java.util.*

class GridHeroAdapter(val listHeroes: ArrayList<Hero>) : RecyclerView.Adapter<GridHeroAdapter.GridViewHolder>() {
    var onItemClickListener: ((Hero) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_heroes, parent, false)
        return GridViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listHeroes.size
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
                .load(listHeroes[position].photo)
                .apply(RequestOptions().override(350, 550))
                .into(holder.imgPhoto)
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)

        init {
            itemView.setOnClickListener {
                onItemClickListener?.invoke(listHeroes[adapterPosition])
            }
        }
    }
}