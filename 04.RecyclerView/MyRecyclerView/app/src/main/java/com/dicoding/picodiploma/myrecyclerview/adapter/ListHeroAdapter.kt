package com.dicoding.picodiploma.myrecyclerview.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.myrecyclerview.R
import com.dicoding.picodiploma.myrecyclerview.model.Hero
import java.util.*

class ListHeroAdapter(val listHeroes: ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.CategoryViewHolder>() {
    var onItemClickListener: ((Hero) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemRow = LayoutInflater.from(parent.context).inflate(R.layout.item_row_heroes, parent, false)
        return CategoryViewHolder(itemRow)
    }

    override fun getItemCount(): Int {
        return listHeroes.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.tvName.text = listHeroes[position].name
        holder.tvFrom.text = listHeroes[position].from

        Glide.with(holder.itemView.context)
                .load(listHeroes[position].photo)
                .apply(RequestOptions().override(55, 55))
                .into(holder.imgPhoto)
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvFrom: TextView = itemView.findViewById(R.id.tv_item_from)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)

        init {
            itemView.setOnClickListener {
                onItemClickListener?.invoke(listHeroes[adapterPosition])
            }
        }
    }
}
